<template>
    <div class="post">
        <input type="file" accept="image/*" capture="camera" />
        <button class="btn" @click="test"> 히히힣 </button>

    </div>
</template>
 
<script>
    import '../../assets/css/post.scss'
    import axios from "axios";
    import { mapGetters } from "vuex";

    export default {
        name:"Post",
        components:{
        },
        watch: {
        },
        created() {
        },
        methods: {
            test() {
                axios.get("http://127.0.0.1:8080/test")
                .then(res => console.log(res))
                .catch(err => console.log(err.response))
            }
        },
        data: () => {
            return {
            }
        }
    }

</script>