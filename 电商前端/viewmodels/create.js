var app = new Vue({
    el: '#app',
    data: {
        productCode: '',
        productName: '',
        price: '',
        discount: '',
        stockQuantity: '', 
        rewordPoints: '',
        sortOrder:'',
        description:'',
        selectedStatus: 1,
        statuses:[
            { value: 0, label: '下架' },
            { value: 1, label: '上架'},
            { value: 2, label: '待审核'}
        ],
        selectedMainPic: '',
        mainPicUrl: '',
        selectedOtherPics: [],
        otherPicUrls: [],
        mainFileList: [],
        otherFileList: [],
        productAbstract: ''

    },
    mounted() {
        console.log('view mounted');
        tinymce.init({
            selector: '#mytextarea'
        });
    },
    methods: {
        handleOnMainChange(val){
            this.selectedMainPic=val.raw;
        },
        uploadMainImage(){
            var forData=new FormData();
            forData.append("image",this.selectedMainPic);
            axios.post('/image/upload', forData,{
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            })
              .then(function (response) {
                  app.mainPicUrl=response.data;
                console.log(response);
              })
              .catch(function (error) {
                console.log(error);
              });

        },
        handleUploadMainClick(){
            this.uploadMainImage();
        },


        handleOnOtherChange(file, fileList){
            this.selectedOtherPics=fileList;
        },
        handleOnOtherRemove(file, fileList){
            this.selectedOtherPics=fileList;
        },
        uploadOtherImage(){
            app.selectedOtherPics.forEach(pic => {
                var forData=new FormData();
            forData.append("image",pic.raw);
            axios.post('/image/upload', forData,{
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            })
              .then(function (response) {
                  var url=response.data;
                  app.otherPicUrls.push(url);
                console.log(response);
              })
              .catch(function (error) {
                console.log(error);
              });

            });
        },
        handleUploadOtherClick(){
            this.uploadOtherImage();
        },
        handleCreateClick(){
            this.description = tinyMCE.activeEditor.getContent();
            this.createProduct();
        },
        createProduct() {
            axios.post('/product/create', {
                productCode: this.productCode,
                productName: this.productName,
                price: this.price,
                discount: this.discount,
                stockQuantity: this.stockQuantity,
                status: this.selectedStatus,
                mainPicUrl: this.mainPicUrl,
                rewordPoints: this.rewordPoints,
                sortOrder: this.sortOrder,
                description: this.description,
                othPicUrls: this.otherPicUrls,
                productAbstract: this.productAbstract
            })
                .then(function (response) {
                    console.log(response);
                    alert('创建成功');
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    },
})