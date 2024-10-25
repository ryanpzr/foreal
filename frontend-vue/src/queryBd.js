export async function updateImgBd(id, newImgValue) {
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

export async function updateLikeState(id, likeState) {
    const url = 'http://localhost:8080/auth/home/updateLikeState';

    console.log(likeState)

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