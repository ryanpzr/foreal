document.addEventListener("DOMContentLoaded", function () {
    fetchArticle();

    var modal1 = document.getElementById("formPopup1");
    var btn1 = document.getElementById("openFormButton1");
    var span1 = modal1.getElementsByClassName("close-button")[0];

    btn1.onclick = function () {
        modal1.style.display = "block";
    }

    span1.onclick = function () {
        modal1.style.display = "none";
    }
});

async function fetchArticle() {
    const main = document.getElementById("le_news_container");

    try {
        const response = await fetch('http://localhost:8080/auth/home/buscarBreaking', {
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
                const novoItem = criarNoticiaNoIndex(card);
                if (novoItem instanceof HTMLElement) {
                    main.appendChild(novoItem);

                } else {
                    console.error('criarNoticiaNoIndex did not return a valid DOM element');
                }
            });

            // Adiciona o evento de clique para links de comentários após adicionar todos os artigos
            const commentsLinks = document.querySelectorAll(".comments-link");
            commentsLinks.forEach(link => {
                link.addEventListener("click", (event) => {
                    event.preventDefault();
                    const commentsSection = link.nextElementSibling;

                    if (commentsSection) {
                        if (commentsSection.style.maxHeight) {
                            commentsSection.style.maxHeight = null;
                        } else {
                            commentsSection.style.maxHeight = commentsSection.scrollHeight + "px";
                        }
                    }
                });
            });

            // Evento de clique para botões de comentar
            const commentButtons = document.querySelectorAll(".comment-button");
            commentButtons.forEach(button => {
                button.addEventListener("click", (event) => {
                    const modal2 = document.getElementById("formPopup2");
                    modal2.style.display = "block";

                    // Armazena o ID do post no modal para uso posterior
                    const postId = event.target.getAttribute("data-id");
                    modal2.setAttribute("data-post-id", postId);
                });
            });

            // Fecha o modal ao clicar no botão de fechar
            const span2 = document.querySelector("#formPopup2 .close-button");
            span2.onclick = function () {
                const modal2 = document.getElementById("formPopup2");
                modal2.style.display = "none";
            };

            // Fecha o modal se clicar fora dele
            window.onclick = function (event) {
                const modal1 = document.getElementById("formPopup1");
                const modal2 = document.getElementById("formPopup2");
                if (event.target == modal1) {
                    modal1.style.display = "none";
                } else if (event.target == modal2) {
                    modal2.style.display = "none";
                }
            };

        } else {
            console.error('Data is not an array', data);
        }
    } catch (error) {
        console.error('Error:', error);
    }
}

function criarNoticiaNoIndex(card) {
    const novoItem = document.createElement('container');
    novoItem.classList.add('le_news');

    let comentariosHTML = '';
    if (Array.isArray(card.comentarios)) {
        comentariosHTML = card.comentarios.map(comentario => `
            <div class="comment">
                <p class="comment_author">${comentario.autor}</p>
                <p class="comment_content">${comentario.comentario}</p>
                <p class="news_hour">${comentario.time}</p>
            </div>
        `).join('');
    }

    novoItem.innerHTML = `
        <div class="news-details">
            <h2 class="news_title">${card.titulo}</h2>
            <p class="news_author">${card.autor}</p>
            <p class="news_content">${card.conteudo}</p>
            <p class="news_hour">${card.time}</p>
            
            <button class="vote-btn" onclick="insertLike(event, ${card.id})"><img src="img/likeDefault.png" id="like-${card.id}" data-id="${card.id}"> ${card.gostar}</button>
            <span class="vote-count"></span>
            <button class="vote-btn" onclick="insertDeslike(event, ${card.id})"><img src="img/deslikeDefault.png" id="deslike-${card.id}" data-id="${card.id}"> ${card.desgostar}</button>
        </div>
        <a href="#" class="comments-link">Comentarios</a>
        <div class="comments-section">
         ${comentariosHTML}
            <div class="comment">
                <button type="button" class="comment-button openFormButton2"  data-id="${card.id}">Comentar</button>
            </div>
        </div>
    `;

    // Atualizar o estado dos botões de like e dislike com base no localStorage
    const isLiked = localStorage.getItem('isLiked-' + card.id) === 'true';
    const isDisliked = localStorage.getItem('isDisliked-' + card.id) === 'true';

    const imgLike = novoItem.querySelector(`#like-${card.id}`);
    const imgDislike = novoItem.querySelector(`#deslike-${card.id}`);

    if (isLiked) {
        imgLike.src = 'img/likeClicked.png';
    } else {
        imgLike.src = 'img/likeDefault.png';
    }

    if (isDisliked) {
        imgDislike.src = 'img/deslikeClicked.png';
    } else {
        imgDislike.src = 'img/deslikeDefault.png';
    }

    return novoItem;
}

function enviarComentarios(event) {
    event.preventDefault();

    var autor = document.getElementById('author2').value;
    var comentario = document.getElementById('comment2').value;

    var modal2 = document.getElementById("formPopup2");
    var id = modal2.getAttribute("data-post-id");

    var dados = {
        comentario: comentario,
        autor: autor,
        id: id
    };

    fetch('http://localhost:8080/auth/comentario/postarComentario', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(dados)
    })
        .then(response => {
            if (response.ok) {
                console.log('Dados enviados com sucesso!');
                alert("Dados enviados com sucesso!")
                location.reload();
                limparCamposDoFormulario("infoForm2")

            } else {
                console.error('Erro ao enviar os dados:', response.statusText);
                alert("Erro ao enviar os dados")
                limparCamposDoFormulario("infoForm2")
            }
        })
        .catch(error => {
            console.error('Erro ao enviar os dados:', error);
        });
}

function limparCamposDoFormulario(formId) {
    var formulario = document.getElementById(formId);

    if (formulario) {
        for (var i = 0; i < formulario.elements.length; i++) {
            var elemento = formulario.elements[i];

            if (elemento.tagName === 'TEXTAREA') {
                elemento.value = '';
            }
        }
    } else {
        console.error('Formulário não encontrado:', formId);
    }
}

function insertLike(event, postId) {
    event.preventDefault();

    const button = event.currentTarget;
    const img = button.querySelector('img');
    const id = postId;

    if (img.src.includes('likeDefault.png')) {
        img.src = 'img/likeClicked.png';
        localStorage.setItem('isLiked-' + id, 'true');

        const dados = {
            id: id,
            likeParam: 1
        }

        fetch('http://localhost:8080/auth/home/like', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dados)
        })
            .then(response => {
                if (response.ok) {
                    console.log('Like enviado com sucesso!');
                    location.reload();

                } else {
                    console.error('Erro ao enviar o like:', response.statusText);
                }
            })
            .catch(error => {
                console.error('Erro ao enviar o like:', error);
            });

    } else {
        img.src = 'img/likeDefault.png';
        localStorage.setItem('isLiked-' + id, 'false');

        const dados = {
            id: id,
            likeParam: -1
        };

        fetch('http://localhost:8080/auth/home/like', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dados)
        })
            .then(response => {
                if (response.ok) {
                    console.log('Deslike enviado com sucesso!');
                    location.reload();

                } else {
                    console.error('Erro ao enviar o deslike:', response.statusText);
                }
            })
            .catch(error => {
                console.error('Erro ao enviar o deslike:', error);
            });
    }
}

function insertDeslike(event, postId) {
    event.preventDefault();

    const button = event.currentTarget;
    const img = button.querySelector('img');
    const id = postId;

    if (img.src.includes('deslikeDefault.png')) {
        img.src = 'img/deslikeClicked.png';
        localStorage.setItem('isDisliked-' + id, 'true');

        const dados = {
            id: id,
            likeParam: 1
        };

        fetch('http://localhost:8080/auth/home/deslike', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dados)
        })
            .then(response => {
                if (response.ok) {
                    console.log('Dislike enviado com sucesso!');
                    location.reload();

                } else {
                    console.error('Erro ao enviar o dislike:', response.statusText);
                }
            })
            .catch(error => {
                console.error('Erro ao enviar o dislike:', error);
            });

    } else {
        img.src = 'img/deslikeDefault.png';
        localStorage.setItem('isDisliked-' + id, 'false');

        const dados = {
            id: id,
            likeParam: -1
        };

        fetch('http://localhost:8080/auth/home/deslike', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dados)
        })
            .then(response => {
                if (response.ok) {
                    console.log('Dislike removido com sucesso!');
                    location.reload();

                } else {
                    console.error('Erro ao remover o dislike:', response.statusText);
                }
            })
            .catch(error => {
                console.error('Erro ao remover o dislike:', error);
            });
    }
}






