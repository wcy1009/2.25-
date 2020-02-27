var app = new Vue({
    el: '#app',
    data: {
        pageInfo: '',
        pageNum: 1
    },
    mounted() {
        this.searchProudcut();
    },
    methods: {
        handlePageChange(val){
            this.pageNum=val;
            this.searchProudcut();
        },
     searchProudcut(){
        axios.get('/product/search', {
            params: {
              pageNum: this.pageNum
            }
          })
          .then(function (response) {
              app.pageInfo=response.data;
            console.log(response);
          })
          .catch(function (error) {
            console.log(error);
          })
     }   
    }
})