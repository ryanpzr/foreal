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

export async function getSearchedPost(item) {
    const url = 'http://localhost:8080/auth/home/getPostSearched/' + item;
    try {
        const response = await fetch(url);

        if (!response.ok) {
            throw new Error(`Response status: ${response.status}`);
        }

        return await response.json();

    } catch (error) {
        console.log('Erro ao buscar o post pesquisado:', error);
    }

}

export function getImgLike(likeState) {
    if(likeState) {
        return require('../assets/img/like-clicado.png')
    } 
    return require('../assets/img/like.png')
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
        updateLikeState(jsonElement.id, jsonElement.likeState);
        jsonElement.pathImgLike = require('../assets/img/like-clicado.png')
    } else {
        updateLikeState(jsonElement.id, jsonElement.likeState);
        jsonElement.pathImgLike = require('../assets/img/like.png');
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

export async function saveAccountInfo(formData) {
    const url = 'http://localhost:8080/auth/account/postAccountConfig'

    try {
        const response = await fetch(url, {
            method: 'POST',
            body: formData
        })

        if (response.ok) {
            console.log("Requisição realizada com sucesso")
            return true;
        } else {
            console.error("Falha na resposta do servidor");
            return false;
        }

    } catch {
        console.error("Falha na requisição", error);
    }

}

export async function getAccountInfo() {
    const url = 'http://localhost:8080/auth/account/getAccountInfo'

    try {
        const response = await fetch(url)

        if (response.ok) {
            console.log("Requisição realizada com sucesso")
            return response.json();
        } else {
            console.error("Falha na resposta do servidor");
            return false;
        }

    } catch {
        console.error("Falha na requisição", error);
    }

}
