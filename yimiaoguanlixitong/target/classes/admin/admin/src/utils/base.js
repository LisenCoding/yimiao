const base = {
    get() {
        return {
            url : "http://localhost:8080/zaixinyimiaoyuyue/",
            name: "zaixinyimiaoyuyue",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/zaixinyimiaoyuyue/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "疫苗管理系统"
        } 
    }
}
export default base
