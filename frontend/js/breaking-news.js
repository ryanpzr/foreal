document.addEventListener("DOMContentLoaded", function() {
    fetchArticle();
});

async function fetchArticle() {
    try {
        const response = await fetch('http://localhost:3000/article'); // Replace with your actual endpoint
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        const articleData = await response.json();
        displayArticle(articleData);
    } catch (error) {
        console.error('Fetch error:', error);
    }
}

function displayArticle(data) {
    document.querySelector('.news_title').innerText = data.title;
    document.querySelector('.news_author').innerText = 'por ' + data.author;
    document.querySelector('.news_content').innerText = data.content;
    document.querySelector('.news_hour').innerText = data.date + ' - ' + data.time;
    document.querySelector('.vote-count').innerText = data.likes - data.dislikes;
    displayComments(data.comments);
}

function displayComments(comments) {
    const commentsSection = document.querySelector('.comments-section');
    commentsSection.innerHTML = '';
    comments.forEach(comment => {
        const commentDiv = document.createElement('div');
        commentDiv.className = 'comment';
        commentDiv.innerHTML = `
            <p class="comment_author">${comment.author} diz</p>
            <p class="comment_content">${comment.content}</p>
            <p class="news_hour">${comment.date} - ${comment.time}</p>
        `;
        commentsSection.appendChild(commentDiv);
    });
}