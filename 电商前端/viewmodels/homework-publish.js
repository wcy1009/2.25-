var app = new Vue({
    el: '#app',
    data: {
        courses:[],
        selectedCourseId:'',
        clazzes:[],
        selectedClazzId:'',
        teachers:[],
        selectedTeacherId:'',
        selectedCourseUnit:'',
        deadTime:'',
        abstractContent:''
    },
    mounted() {
        console.log('view mounted');
        this.getAllCourse();
        this.getAllClazz();
        this.getAllTeacher();
    },
    methods: {
        getAllCourse() {
            axios.get('http://localhost:8080/course/getAll')
                .then(function (response) {
                    console.log(response);
                    app.courses = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        getAllClazz() {
            axios.get('http://localhost:8080/clazz/getAll')
                .then(function (response) {
                    console.log(response);
                    app.clazzes = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        getAllTeacher() {
            axios.get('http://localhost:8080/teacher/getAll')
                .then(function (response) {
                    console.log(response);
                    app.teachers = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        handlePublishClick(){
            console.log("publish click");
            this.doHomeworkPublish();
        },
        doHomeworkPublish(){
            axios.post('http://localhost:8080/homework/publish',{
                courseId: this.selectedCourseId,
                courseUnit: this.selectedCourseUnit,
                teacherId: this.selectedTeacherId,
                clazzId: this.selectedClazzId,
                deadTime: this.deadTime.getTime(),
                abstractContent: this.abstractContent,
                attatchments:[]
            })
            .then(function (response) {
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
                
        }
    },
})