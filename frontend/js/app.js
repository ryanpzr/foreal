document.addEventListener("DOMContentLoaded", function() {
    fetchArticle();
});

async function fetchArticle() {
    const main = document.getElementById("le_news");

    try {
        const response = await fetch('http://localhost:8080/auth/home/buscarDadosHome', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });

        if (!response.ok) {
            throw new Error('Erro ao buscar dados do backend');
        }

        const data = await response.json();

        if (Array.isArray(data)) {
            data.forEach(card => {
                const novoItem = criarNoticiaNoIndex(card); // Ensure this function is defined
                if (novoItem instanceof HTMLElement) { // Check if novoItem is a valid DOM element
                    main.appendChild(novoItem); // Ensure main is defined
                } else {
                    console.error('criarNoticiaNoIndex did not return a valid DOM element');
                }
            });
        } else {
            console.error('Data is not an array', data);
        }
    } catch (error) {
        console.error('Error:', error);
    }
}

function criarNoticiaNoIndex(card) {

    const novoItem = document.createElement('article');
    novoItem.classList.add('le_news');

    novoItem.innerHTML = `
                <div class="vote-section">
                    <button class="vote-btn">▲</button>
                    <span class="vote-count">n</span>
                    <button class="vote-btn">▼</button>
                </div>
                <div class="news-details">
                    <h2 class="news_title">${card.titulo}</h2>
                    <p class="news_author">${card.autor}</p>
                    <p class="news_content">${card.conteudo}</p>
                    <p class="news_hour">${card.time}</p>

                    <a href="#" class="comments-link">Comentarios</a>
                    
                    <div class="comments-section">
                        <div class="comment">
                            <button type="button" class="comment-button"  id="openFormButton2">Comentar</button>
                        </div>
                        <div class="comment">
                                <p class="comment_author">${card.comentarios.autor}</p>
                                <p class="comment_content">${card.comentarios.comentario}</p>
                                <p class="news_hour">${card.comentarios.time}</p>
                        </div>
                    </div>
                </div>
    `;

    return novoItem;
}