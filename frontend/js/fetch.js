fetch("https://pokeapi.co/api/v2/pokemon/Ninetales", {
    method: 'POST',
    headers: {
        'Content-Type': 'Aplication/json'
    },
    body: JSON.stringify({
        name: 'Rogerio'
    })
})
.then(res => {
    if (res.ok) {
        console.log('Success')
    } else {
        console.log('Failure')
    }
    return res.json()
})
.then(data => console.log(data))
.catch(error => console.error(error));