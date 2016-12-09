//load home page functionality
$(document).ready(function () {
    loadPosts(); 

    $("#searchButton").click(function (event) {
        var searchCategory = $("#searchCategory").val();
        var rows = $("#HomeRows");

        event.preventDefault();
        if (searchCategory === "Title") {
            getTitle();
        }
        if (searchCategory === "Category") {
            getCategory();
        }
        if (searchCategory === "Author") {
            getAuthor();
        }
        if (searchCategory === "Tags") {
            getTags();
        }
        if (searchCategory === "---") {
            rows.empty();
            var choose = $("<h3 style='color:red'>");
            choose.append("Please choose a search criteria");
            rows.append(choose);
        }
    });
});


function clearTable() {
    $("#HomeRows").empty();
}

//process the posts

function processBlogList(data) {

    clearTable();
    var rows = $("#HomeRows");
    var counter = 0;

    $.each(data, function (index, blog) {

        var titleField = $("<div style='font-weight: bold'>");
        var dateField = $("<div>");
        var authorField = $("<div>");
        var categoryField = $("<div>");
        var contentField = $("<div>");
        var tagsField = $("<div align='right'>").text(blog.tags);


        titleField.append($("<h4>").append($("<a>").attr({
                    'onclick' : 'goToBlog(' +blog.id+ ')'
                }).text(blog.title)));
        dateField.text(blog.date);
        authorField.text(blog.author);
        categoryField.text(blog.category);
        contentField.append(blog.content);

        var row = $('<div class="row-buffer">');

        row.append(dateField);
        row.append(titleField);
        row.append(categoryField);
        row.append(contentField);
        row.append(authorField);
        row.append(tagsField);
        rows.append(row);

        counter++;

    });

    if (counter === 0) {
        var noResults = $("<h3 style='color:red'>");
        noResults.append("No results were found<br/>");
        noResults.append("If searching by tags must preface with '#'<br/> #exampleTagsSearch");
        rows.append(noResults);
    }
}

function loadPosts() {

    $.ajax({
        url: "blogsPublished/",
        type: "GET"
    }).success(function (data) {
        processBlogList(data);
    });
}

function getCategory() {

    var searchInfo = $("#searchInfo").val();

    if (searchInfo === "") {
        loadPosts();
    } else {
        $.ajax({
            url: "blogListDisplayCategory/" + searchInfo,
            type: "GET",
            headers: {
                "Accept": "application/json"
            }
        }).success(function (data) {
            processBlogList(data);
        });
    }
}

function getAuthor() {

    var searchInfo = $("#searchInfo").val();

    if (searchInfo === "") {
        loadPosts();
    } else {
        $.ajax({
            url: "blogListDisplayAuthor/" + searchInfo,
            type: "GET",
            headers: {
                "Accept": "application/json"
            }
        }).success(function (data) {
            processBlogList(data);
        });
    }
}

function getTitle() {

    var searchInfo = $("#searchInfo").val();

    if (searchInfo === "") {
        loadPosts();
    } else {
        $.ajax({
            url: "blogListDisplayTitle/" + searchInfo,
            type: "GET",
            headers: {
                "Accept": "application/json"
            }
        }).success(function (data) {
            processBlogList(data);
        });
    }
}

function getTags() {

    var searchInfo = $("#searchInfo").val();
  
        $.ajax({
            url: 'searchTags',
            type: 'POST',
            data: searchInfo,                 
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'text/plain'
            }
        }).success(function (data){
            processBlogList(data);
        });
}

function goToBlog(blogID){
    $.ajax({
        url: "singleBlog/" + blogID,
        type: "GET",
        headers: {
            'Accept' : 'application/json'
        }
    }).success(function(blog){
        window.location="http://localhost:8080/BlogCapstone/singleBlog/"+ blogID;
    });
}
