package com.ute.web.controllers;


import at.favre.lib.crypto.bcrypt.BCrypt;
import com.ute.web.beans.User;
import com.ute.web.models.UserModel;
import com.ute.web.recaptcha.VerifyUtils;
import com.ute.web.utils.ServletUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Random;


@WebServlet(name = "AccountServlet", value = "/Account/*")
public class AccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path == null || path.equals("/")) {
            path = "/404";
        }
        switch (path) {
            case "/Otp":
                ServletUtils.forward("/views/vwAccount/Otp.jsp" , request , response);
                break;
            case "/Register":
                ServletUtils.forward("/views/vwAccount/Register.jsp", request, response);
                break;
            case "/Login":
                HttpSession session = request.getSession();
                if ((boolean) session.getAttribute("auth")) {
                    ServletUtils.redirect("/Home", request, response);
                } else
                    ServletUtils.forward("/views/vwAccount/Login.jsp", request, response);
                break;
            case "/Profile":
                ServletUtils.forward("/views/vwAccount/Profile.jsp", request, response);
                break;
            case "/IsAvailable":
                String username = request.getParameter("user");
                User user = UserModel.findByUsername(username);
                boolean isAvailable = (user == null);
                PrintWriter out = response.getWriter();
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                out.print(isAvailable);
                out.flush();
                break;
            default:
                ServletUtils.forward("/views/404.jsp", request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String path = request.getPathInfo();
        switch (path) {
            case "/Otp":
                otpUser(request,response);
                break;
            case "/Register":
                registerUser(request, response);
                break;
            case "/Login":
                loginUser(request, response);
                break;
            case "/Logout":
                logoutUser(request, response);
                break;
            default:
                ServletUtils.forward("/views/404.jsp", request, response);
                break;
        }

    }

    private void otpUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        int Code = Integer.parseInt(request.getParameter("code"));
        String codeString = request.getParameter("code");
        int codeInt =  Integer.parseInt(codeString);
        User user = UserModel.findByCode(codeInt);
        if(user == null){
            request.setAttribute("hasError", true); //Thông báo lỗi
            request.setAttribute("errorMessage", "Sai mã OTP"); // Thông báo lỗi
            ServletUtils.forward("/views/vwAccount/Otp.jsp", request, response);
        }else{
            int id = user.getId();
            int permission = 3;
            int code = 1;
            String bcryptHashString = user.getPassword();
            LocalDate Dob = user.getDob();
            String username = user.getUsername();
            String name = user.getName();
            String email = user.getEmail();
            User User = new User(id , permission, code , username, bcryptHashString, name, email, Dob);
            UserModel.update(User);
            HttpSession session = request.getSession();
            session.setAttribute("otp", false);
            session.setAttribute("auth", true);
            session.setAttribute("authUser", user);//Tắt quyền vào trang OTP
            String url = "/Home";
            ServletUtils.redirect(url, request, response);
        }

    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean valid = true;
        String errorString = null;
        Random dice = new Random();
        int numberRandom = 100000 + dice.nextInt(899999);
        int code = numberRandom;

        String rawpwd = request.getParameter("rawpwd");
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, rawpwd.toCharArray());

        String strDob = request.getParameter("dob");
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");//theo format của java
        LocalDate Dob = LocalDate.parse(strDob, df);

        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");

        if (valid) {
//            System.out.println("gRecaptchaResponse=" + gRecaptchaResponse);
            // Verify CAPTCHA.
            valid = VerifyUtils.verify(gRecaptchaResponse);
            if (!valid) {
                errorString = "Captcha invalid!";
            }
        }
        if (!valid) {
            request.setAttribute("errorString", errorString);
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/vwAccount/Register.jsp");
            dispatcher.forward(request, response);
            return;
        } else {
            //Note: tạo riêng một class java gửi Email rồi gọi nó ra.
            final String usernameEmail = "19110186@student.hcmute.edu.vn";
            final String passwordEmail = "Dat123456789";
            String emailSubject = "Mã OTP";
            String emailContent = String.valueOf(numberRandom);

            Properties prop = new Properties();
            prop.put("mail.smtp.host" , "smtp.gmail.com");
            prop.put("mail.smtp.port" , "587");
            prop.put("mail.smtp.auth" , "true");
            prop.put("mail.smtp.starttls.enable" , "true");
            Session sessionEmail = Session.getInstance(prop, new javax.mail.Authenticator(){
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(usernameEmail, passwordEmail);
                }
            });
            //Đăng nhập được email
            try {
                Message message = new MimeMessage(sessionEmail);
                message.setFrom(new InternetAddress(usernameEmail));
                message.setRecipients(
                        Message.RecipientType.TO, InternetAddress.parse(email)
                );
                message.setSubject(emailSubject);
                message.setText(emailContent);
                Transport.send(message);
            }catch (Exception e){

            }
            int permission = 4;
            User user = new User(permission, code , username, bcryptHashString, name, email, Dob);
            UserModel.add(user);
            HttpSession session = request.getSession(); //Dùng chung cho mọi request
            session.setAttribute("otp", true); //Bật quyền vào trang OTP
            ServletUtils.redirect("/Account/Otp", request, response);
        }
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = UserModel.findByUsername(username);
        if (user != null & user.getPermission() != 4) {
            BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
            if (result.verified) {
                HttpSession session = request.getSession(); //Dùng chung cho mọi request
                session.setAttribute("auth", true);
                session.setAttribute("authUser", user);

                String url = "/Home";
                ServletUtils.redirect(url, request, response);
            } else {
                request.setAttribute("hasError", true); //Thông báo lỗi
                request.setAttribute("errorMessage", "Invalid login."); // Thông báo lỗi
                ServletUtils.forward("/views/vwAccount/Login.jsp", request, response);
            }
        } else {
            request.setAttribute("hasError", true); //Thông báo lỗi
            request.setAttribute("errorMessage", "Invalid login."); // Thông báo lỗi
            ServletUtils.forward("/views/vwAccount/Login.jsp", request, response);
        }
    }

    private void logoutUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("auth", false);
        session.setAttribute("authUser", new User());
        String url = request.getHeader("referer");
        if (url == null)
            url = "/Home";
        ServletUtils.redirect(url, request, response);
    }
}
