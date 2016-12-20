<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Admin Page - Boss</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Calligraffitti" rel="stylesheet">
        <link href = "https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
              rel = "stylesheet">
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
                toolbar: 'insertfile undo redo | styleselect | forecolor backcolor bold italic underline \n\
                | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent \n\
                | link charmap image emoticons | preview save',
                images_upload_base_path: '${pageContext.request.contextPath}/img'
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
        <script type="text/javascript">
            tinymce.init({
                selector: '#page-edit-content',
                min_width: 400,
                min_height: 300,
                plugins: [
                    'wordcount image imagetools'
                ]
            });
        </script>
        <script type="text/javascript">
            tinymce.init({
                selector: '#editBlogContent',
                min_width: 400,
                min_height: 300,
                plugins: [
                    'wordcount image imagetools'
                ]
            });
        </script>
    </head>
    <body>
        <div class="text-center" id="blogName">
            <h1>Amelia's Blog</h1>
            <h4>Storm Chasing Baby Saving Laser Jet Philanthropist</h4><br>
        </div> 
        <hr/>
        <div class="container">
        <%@include file="headerFragment.jsp" %>
            <ul class="nav nav-pills">
                <li class="active">
                    <a href="#blogs" data-toggle="tab">Blogs</a>
                </li>
                <li>
                    <a href="#pages" data-toggle="tab">Pages</a>
                </li>
            </ul>
            <div class="tab-content clearfix tab-format">
                <div class="tab-pane active" id="blogs">
                    <div class="adminSection">
                        <h4>Blog Drafts</h4>
                        <table id="draftTable" class="display table table-hover">
                            <thead>
                                <tr>
                                    <th width="15%">Date</th>
                                    <th width="15%">Author</th>
                                    <th width="40%">Blog Title</th>
                                    <th width="10%"></th>
                                    <th width="10%"></th>
                                    <th width="10%"></th>
                                </tr>
                            </thead>
                            <!--                        define a placeholder to be replaced w dynamic content w jQuery-->
                            <tbody id="draftRows" class="read-more-wrap"></tbody>
                        </table>
                        <button class="btn btn-default" id="blogExpand">Show All</button>
                    </div>
                    <div class="adminSection" id="published">
                        <h4>Published Blogs</h4>
                        <table id="publishedTable" class="table table-hover">
                            <tr>
                                <th width="15%">Date</th>
                                <th width="15%">Author</th>
                                <th width="40%">Blog Title</th>
                                <th width="10%"></th>
                                <th width="10%"></th>
                                <th width="10%"></th>
                            </tr>
                            <!--                        define a placeholder to be replaced w dynamic content w jQuery-->
                            <tbody id="publishedRows" class="read-more-wrap"></tbody>
                        </table>
                        <button class="btn btn-default" id="blogPublishedExpand">Show All</button>
                    </div>
                    <form class="form-horizontal adminSection" id="blogForm">
                        <h4>Add Blog</h4>
                        <hr>
                        <div class="form-group">
                            <label for="addBlogCategory" class="col-sm-1 control-label">Category</label>
                            <div class="col-sm-4">
                                <select name="add-blog-category" class="form-control" id="addCategory">
                                    <c:forEach items="${categories}" var="category">
                                        <option value="${category}">${category}</option>
                                    </c:forEach>

                                    <option value="addNewCategory">Add New Category...</option>
                                </select>
                            </div>
                            <div class="col-sm-4">
                                <input id="newCategory" class="form-control" type="text" placeholder="New Category" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="addBlogTitle" class="col-sm-1 control-label">Title</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="addTitle" placeholder="Title">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="addBlogDate" class="col-sm-1 control-label">Date</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="datePicker" placeholder="Click to add date">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="addBlogContent" class="weak col-sm-1 control-label">Content</label>
                            <div class="col-sm-11">
                                <textarea name="addBlogContent" id="addBlogContent"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="addBlogTags" class="col-sm-offset-4 col-sm-4 control-label">Hashtags (Separate by spaces)</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="addTags" placeholder="put tags here">
                            </div>
                        </div>
                        <div class="form-group">
                            <div id="validationErrors" class="alert alert-danger"
                                 style="display:none"><p>Make sure all fields are filled out!</p></div>   
                            <div class="pull-right addButtonGroup">
                                <button type="submit" class="btn btn-lg btn-default addButton" id="draftBlog">Save Draft</button>
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <button type="submit" class="btn btn-lg btn-default addButton" id="publishBlog">Publish</button>
                                </sec:authorize>
                            </div> 
                        </div>
                    </form> 

                </div>

                <div class="tab-pane" id="pages">
                    <div class="adminSection">
                        <h4>Pages</h4>
                        <table id="pageTable" class="table table-hover">
                            <tr>
                                <th width="60%">Blog Title</th>
                                <th width="20%">Edit</th>
                                <th width="20%">Delete</th>
                            </tr>
                            <!--                        define a placeholder to be replaced w dynamic content w jQuery-->
                            <tbody id="pageRows"></tbody>
                        </table>
                        <button class="btn btn-default" id="pageExpandButton">Show All</button>
                    </div>
                    <form class="form-horizontal adminSection">
                        <h4>Add Page</h4>
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
                            <div id="validationPageErrors" class="alert alert-danger"
                                 style="display:none"></div>
                            <div class="col-sm-offset-10 col-sm-2">                               
                                <button type="submit" class="btn btn-lg btn-default pull-right" id="addPageButton">Add Page</button>
                            </div>
                        </div>
                    </form>

                </div>

            </div>


        </div>
        <%@include file="editBlogModalFragment.jsp"%>
        <%@include file="editPageModalFragment.jsp" %>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <script src="${pageContext.request.contextPath}/js/blogAdmin.js"></script>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_USER')">
            <script src="${pageContext.request.contextPath}/js/blogUser.js"></script>
        </sec:authorize>
        <script src="${pageContext.request.contextPath}/js/pageAdmin.js"></script>
        <script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    </body>
</html>

