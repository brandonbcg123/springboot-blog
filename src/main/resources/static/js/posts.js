(function($) {
    var request = $.ajax({'url': '/posts.json'});
    request.done(function (posts) {
        var html = '';
        posts.forEach(function(post) {
            html += '<div class="post">';
            html += '<h2 id="post-title" class="show-title">' + post.title + '</h2>';
            html += '<h4 class="show-body">' + post.body + '</h4>';
            // html += '<p>Published by ' + post.user.username + '</p>';
            html += '</div>';
        });
        $('#posts').html(html);
    });
})(jQuery);