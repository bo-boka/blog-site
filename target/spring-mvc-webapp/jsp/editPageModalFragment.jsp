<div class="modal fade" id="editPageModal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    &times;
                </button>
            </div>  
            <div class="modal-body">
                <form class="form-horizontal adminSection">
                    <h4>Edit Page</h4><p id="page-edit-id"></p>
                    <div class="form-group">
                        <label for="editPageTitle" class="col-sm-1 control-label">Title: </label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="page-edit-title" placeholder="Title">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editPageContent" class="weak col-sm-1 control-label">Content</label>
                        <div class="col-sm-11">
                            <textarea name="editPageContent" id="page-edit-content"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div id="validationPageEditErrors" class="alert alert-danger"
                                 style="display:none"></div>                           
                        <div class="col-sm-offset-10 col-sm-2">
                            <button type="submit" class="btn btn-lg btn-default pull-right" id="editPageButton">Save Changes</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
