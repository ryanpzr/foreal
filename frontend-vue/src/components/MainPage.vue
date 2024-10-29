<template>
    <main class="main">
        <div class="main-left">
            <SectionOptions />
        </div>

        <div id="main-right" class="main-right">
            <br><br>
            <Card />
        </div>

        <AddPost class="card-addPost"
            v-show="postState"
            @actionButtonCloseCard="actionButtonClose"
        ></AddPost>
    </main>
</template>

<script>
import SectionOptions from './SectionOptions.vue';
import Card from './Card.vue';
import AddPost from './AddPost.vue';

export default {
    components: {
        SectionOptions,
        Card,
        AddPost
    },
    props: {
        postState: {type: Boolean, required: true}
    },
    methods: {
        actionButtonClose(state) {
            this.$emit('componentAddPost', state)
        }
    }
}
</script>

<style>

.main {
    display: flex;
}

.main-left {
    width: 20vw;
    height: 100vh;
    margin-top: 8vh;
    background-color: black;
}

.main-right {
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: center;
    width: 80vw;
    height: auto;
    margin-top: 8vh;
    background-color: black;
    border-left: 1px solid rgb(53, 53, 53);
}

.card-addPost {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background: white;
  border: 2px solid #ccc;
  padding: 20px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  z-index: 1000;
}

</style>
