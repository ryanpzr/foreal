<template>
    <nav>
        <div class="nav_left">
            <h1>
                Foreal
            </h1>
        </div>

        <div class="nav_center">
            <h1>
                BARRA DE PESQUISA!
            </h1>
        </div>

        
        <div class="nav_right">
                <button>
                    <h1>botao1</h1>
                </button>

                <button>
                    <h1>botao2</h1>
                </button>

                <button>
                    <h1>botao3</h1>
                </button>
        </div>
    </nav>
</template>

<style scoped>

* {
    margin: 0;
    padding: 0;
}

nav {
    display: flex;
    position: fixed;
    background-color: black;
    width: 100vw;
    height: 8vh;
}

.nav_left {
    width: 19.8vw;
    border-right: 1px solid rgb(53, 53, 53);
}

.nav_left h1 {
    font-size: 30px;
    color: white;
    margin-left: 20px;
    margin-top: 18px;
}

.nav_center {
    width: 60vw;
    align-items: center;
    text-align: center;
}

.nav_center h1 {
    margin-top: 30px;
    font-size: 15px;
    color: white;
}

.nav_right {
    width: 20.2vw;
    text-align: end;
}

button {
    margin-top: 22px;
    margin-right: 10%;
    cursor: pointer;
}

</style>