export async function getAllPosts(mainState) {
    const url = 'http://localhost:8080/auth/home/' + mainState;

    try {
        const response = await fetch(url);
        if (!response.ok) {
            throw new Error(`Response status: ${response.status}`);
        }

        return await response.json();

    } catch (error) {
        console.log('Erro ao buscar os posts:', error);
    }
}

export async function setLike(jsonElement, json) {
    const url = 'http://localhost:8080/auth/home/like';
    var likeState = 0;

    changeImgLike(jsonElement);

    if(jsonElement.likeState) {
        likeState = 1;

    } else {
        likeState = -1;
    }

    const data = {
        id: Number(jsonElement.id),
        likeParam: likeState
    }

    try {
        const response = await fetch(url, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json' 
            },
            body: JSON.stringify(data)
        });

        if (response.ok) {
            const post = json.find(p => p.id === jsonElement.id);
            if (post) {
                if(jsonElement.likeState) {
                    post.gostar += 1;

                } else {
                    post.gostar -= 1;

                }
            }
        }

    } catch (error) {
        console.log('Erro ao buscar os posts:', error);
    }
}

export async function setComment(author, comment, idPost) {
    const url = 'http://localhost:8080/auth/comentario/postarComentario';

    const data = {
        autor: author,
        comentario: comment,
        id: idPost
    }

    try {
        const fetchResponse = await fetch(url, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json' 
            },
            body: JSON.stringify(data)
        })
                    
        if (fetchResponse.ok) {
            console.log('Sucesso na resposta do servidor.')
            return fetchResponse;
        } else {
            console.error("Falha na resposta do servidor.");
            return null;
        }

    } catch (error) {
        console.error("Falha na requisição.", error);
        return null;
    }
}

function changeImgLike(jsonElement) {

    jsonElement.likeState = !jsonElement.likeState;

    if (jsonElement.likeState) {
        updateImgBd(jsonElement.id, '/src/assets/img/like-clicado.png');
        updateLikeState(jsonElement.id, jsonElement.likeState);

        jsonElement.pathImgLike = '/src/assets/img/like-clicado.png'
    } else {
        updateImgBd(jsonElement.id, '/src/assets/img/like.png');
        updateLikeState(jsonElement.id, jsonElement.likeState);

        jsonElement.pathImgLike = '/src/assets/img/like.png'
    }
}

async function updateImgBd(id, newImgValue) {
    const url = 'http://localhost:8080/auth/home/updateImgValue';

    const data = {
        id: id,
        imgValue: newImgValue
    }

    try {
        const response = await fetch(url, {
            method: "PUT",
            headers: {
                'Content-Type': 'application/json' 
            },
            body: JSON.stringify(data)
        });

        if(response.ok) {
        }

    } catch (error) {
        console.log('Erro ao buscar os posts:', error);
    }

}

async function updateLikeState(id, likeState) {
    const url = 'http://localhost:8080/auth/home/updateLikeState';

    const data = {
        id: id,
        likeState: likeState
    }

    try {
        const response = await fetch(url, {
            method: "PUT",
            headers: {
                'Content-Type': 'application/json' 
            },
            body: JSON.stringify(data)
        });

        if(response.ok) {
        }

    } catch (error) {
        console.log('Erro ao buscar os posts:', error);
    }
}

