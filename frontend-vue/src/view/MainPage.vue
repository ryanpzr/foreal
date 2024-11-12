<template>
    <main class="main">
        <div class="main-left">
            <SectionOptions 
            @valueMainState="changeMainState"
            />
        </div>

        <div id="main-right" class="main-right">
            <br><br>
            <Card 
                v-if="this.valueMainState != 'suaConta'"
                :valueMainState="this.valueMainState"
                :valueNewJson="json"
            >
            </Card>
            
            <YourAccount 
                v-else
            >
            </YourAccount>
        </div>

        <AddPostCard class="card-addPost"
            v-show="postState"
            @newJson="saveNewJson"
            @actionButtonCloseCard="actionButtonClose"
        ></AddPostCard>
    </main>
</template>

<script>
import SectionOptions from '../components/SectionOptions.vue';
import Card from '../components/Card.vue';
import AddPostCard from '../components/AddPostCard.vue';
import YourAccount from '../components/YourAccount.vue';

export default {
    data() {
        return {
            valueMainState: '',
            json: {}        
        }
    },
    components: {
        SectionOptions,
        Card,
        AddPostCard,
        YourAccount
    },
    props: {
        postState: {type: Boolean, required: true}
    },
    methods: {
        actionButtonClose(state) {
            this.$emit('componentAddPost', state)
        },
        changeMainState(value) {
            this.valueMainState = value;
        },
        saveNewJson(value) {
            this.json = value;
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
