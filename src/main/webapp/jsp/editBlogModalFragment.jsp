<div class="modal fade" id="editBlogModal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    &times;
                </button>
            </div>    
            <form class="form-horizontal adminSection" id="blogForm">
                <h4>Edit Blog</h4><p id="editId"></p>
                <hr>
                <div class="form-group">
                    <label for="editBlogCategory" class="col-sm-1 control-label">Category</label>
                    <div class="col-sm-4">
                        <select name="edit-blog-category" class="form-control" id="editCategory">
                            <option value="Storms">Storms</option>
                            <option value="Causes">Causes</option>
                            <option value="Events">Events</option>
                            <option value="Philanthropy">Philanthropy</option>
                            <option value="editNewCategory">Add New Category...</option>
                        </select>
                    </div>
                    <div class="col-sm-4">
                        <input id="newCategory" class="form-control" type="text" placeholder="New Category" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="editBlogTitle" class="col-sm-1 control-label">Title</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="editTitle" placeholder="Title">
                    </div>
                </div>
                <div class="form-group">
                    <label for="editBlogDate" class="col-sm-1 control-label">Date</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="editDatePicker" placeholder="Click to add date">
                    </div>
                </div>
                <div class="form-group">
                    <label for="editBlogContent" class="weak col-sm-1 control-label">Content</label>
                    <div class="col-sm-11">
                        <textarea name="editBlogContent" id="editBlogContent"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label for="editBlogTags" class="col-sm-offset-4 col-sm-4 control-label">Hashtags</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="editTags" placeholder="#put #hashtags #here">
                    </div>
                </div>
                <div class="form-group">
                    <div id="validationBlogEditErrors" class="alert alert-danger"
                                 style="display:none"></div>
                    <div class="pull-right editButtonGroup">
                        <button type="submit" class="btn btn-lg btn-default" id="editDraftBlog">Save Draft</button>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <button type="submit" class="btn btn-lg btn-default" id="editPublishBlog">Publish</button>
                        </sec:authorize>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
