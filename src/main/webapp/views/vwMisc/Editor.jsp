<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:main>
    <jsp:attribute name="js">
        <script src="https://cdn.tiny.cloud/1/9kosk9uxbr3p3ee6c30y8nue63sgb0asmrolu84h77a0yq54/tinymce/6/tinymce.min.js" referrerpolicy="origin">
        </script>
        <script>
            tinymce.init({
                selector: '#txtFullDes',
                // entity_encoding: "raw"
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <form action="" method="post">
            <div class="card">
                <h4 class="card-header">
                    WYSIWYG HTML Editor
                </h4>
                <div class="card-body">
                    <div class="form-group">
                        <label for="txtTinyDes">Description</label>
                        <input type="text" class="form-control" id="txtTinyDes" name="TinyDes" autofocus />
                    </div>
                    <div class="form-group">
                        <label for="txtFullDes">Full Description</label>
                        <textarea id="txtFullDes" name="FullDes"></textarea>
                    </div>
                </div>
                <div class="card-footer">
                    <button type="submit" class="btn btn-primary">
                        <i class="fa fa-check" aria-hidden="true"></i>
                        Save
                    </button>
                </div>
            </div>
        </form>
    </jsp:body>
</t:main>