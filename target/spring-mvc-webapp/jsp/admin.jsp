<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Admin Page - Boss</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
        <script src='//cdn.tinymce.com/4/tinymce.min.js'></script>
        <script type="text/javascript">
            tinymce.init({
                selector: '#addBlogContent',
                min_width: 400,
                min_height: 300,
                plugins: [
                    'advlist autolink autosave charmap hr link lists print preview ',
                    ' wordcount visualblocks visualchars image imagetools',
                    'table contextmenu emoticons template',
                    'paste save searchreplace textcolor'
                ],
                contextmenu: "link image",
                imagetools_toolbar: "rotateleft rotateright | flipv fliph | editimage imageoptions",
                toolbar: 'insertfile undo redo | styleselect | forecolor backcolor bold italic underline | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link charmap image emoticons | preview save'
            });
        </script>
        <script type="text/javascript">
            tinymce.init({
                selector: '#addPageContent',
                min_width: 400,
                min_height: 300,
                plugins: [
                    'wordcount image imagetools'
                ]
            });
        </script>
    </head>
    <body>
        <div class="container">
            <h1>Storm Chasing Baby Saving Laser Jet Philanthropist</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/home">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/about">About</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/causes">Causes</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/admin">Admin</a></li>
                </ul>    
            </div>
            <h2>Admin - Boss</h2>
            <br>
            <h4>Blog Drafts</h4>
            <%@include file="draftTableFragment.jsp" %>
            <h4>Published Blogs</h4>
            <%@include file="publishedTableFragment.jsp" %>
            <h4>Pages</h4>
            <%@include file="pageTableFragment.jsp" %>
            <br>
            <h4>Add Blog</h4>
            <div class="container">
                <form class="form-horizontal" id="blogForm">
                    <div class="form-group">
                            <label for="addBlogCategory" class="col-sm-1 control-label">Category</label>
                            <div class="col-sm-4">
                                <select name="add-blog-category" class="form-control" id="addCategory">
                                    <option value="catering">Storms</option>
                                    <option value="privateParty">Causes</option>
                                    <option value="feedback">Events</option>
                                    <option value="other">Other</option>
                                </select>
                            </div>
                    </div>
                    <div class="form-group">
                        <label for="addBlogTitle" class="col-sm-1 control-label">Title</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="addTitle" placeholder="Title">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="addBlogContent" class="weak col-sm-1 control-label">Content</label>
                        <div class="col-sm-11">
                            <textarea name="addBlogContent" id="addBlogContent"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="addBlogTags" class="col-sm-offset-4 col-sm-4 control-label">Tags</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="addTags" placeholder="Tags">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-10 col-sm-2">
                            <button type="submit" class="btn btn-lg btn-default pull-right" id="addButton">Add Blog</button>
                        </div>
                    </div>
                </form>
            </div>

            <br>
            <h4>Add Page</h4>
            <div class="container">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="addPageTitle" class="col-sm-1 control-label">Title</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="addPageTitle" placeholder="Title">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="addPageContent" class="weak col-sm-1 control-label">Content</label>
                        <div class="col-sm-11">
                            <textarea name="addPageContent" id="addPageContent"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-10 col-sm-2">
                            <button type="submit" class="btn btn-lg btn-default pull-right" id="addPageButton">Add Page</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/blogAdmin.js"></script>
        <script src="${pageContext.request.contextPath}/js/pageAdmin.js"></script>

    </body>
</html>

