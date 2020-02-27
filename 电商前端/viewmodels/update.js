var app = new Vue({
    el: '#app',
    data: {
        productId: '',
        productCode: '',
        productName: '',
        price: '',
        discount: '',
        stockQuantity: '',
        rewordPoints: '',
        sortOrder: '',
        description: '',
        selectedStatus: 1,
        selectedMainPic: '',
        mainPicUrl: '',
        selectedOtherPics: [],
        otherPicUrls: [],
        statuses: [
            { value: 0, label: '下架' },
            { value: 1, label: '上架' },
            { value: 2, label: '待审核' }
        ]
    },
    mounted() {
        var url = new URL(location.href);
        this.productId = url.searchParams.get("productId");
        if (!this.productId) {
            alert('productId is null');
            return;
        }
        this.getProductById();
    },
    methods: {
        getProductById() {
            axios.get('/product/getById', {
                params: {
                    productId: this.productId
                }
            })
                .then(function (response) {
                    console.log(response);
                    var product = response.data;
                    app.productId = product.productId;
                    app.productCode = product.productCode;
                    app.productName = product.productName;
                    app.price = product.price;
                    app.discount = product.discount;
                    app.stockQuantity = product.stockQuantity;
                    app.selectedStatus = product.status;
                    app.rewordPoints = product.rewordPoints;
                    app.sortOrder = product.sortOrder;
                    app.mainPicUrl = product.mainPicUrl;
                    app.description = product.description;
                    app.otherPicUrls = product.othPicUrls;
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
})