
(function () {

    var app = angular.module("todo",[]);

    var TodoController = function ($scope, todoService, $log,$filter) {

        function onUpdateScreen(data) {
            $log.info("in controller onUpdateScreen");
            $log.info(data);
            if(data && data.length){
                $scope.todos = data;
            }else{
                $log.info("in controller onUpdateScreen undefined");
                $scope.todos = undefined;
            }
            $log.info($scope.todos);
            $scope.newTodo={};
            $scope.error = undefined;
        }

        function onError(reason) {
            $log.info("in controller onError");
            $log.error(reason);
            $scope.error = reason.data;
        }

        var populateView = function () {
            $log.info("in controller populateView");
            todoService.findAll()
                .then(onUpdateScreen,onError);
        };

        $scope.addTodo = function (newTodo) {
            $log.info("in controller addTodo");
            $log.info(newTodo);
            todoService.addTodo(newTodo)
                .then(populateView,onError);
        };
        $scope.edit = function (todo) {
            $log.info("in controller edit");
            $log.info(todo);
            var todoDueDate = todo.dueDate;
            if((typeof todoDueDate)==="string"){
                $log.info("typeof todoDueDate is string");
                var year = todo.dueDate.substr(0,4);
                var month = todo.dueDate.charAt(5)+""+todo.dueDate.charAt(6);
                var day = todo.dueDate.substr(8,10);
                var newDate = new Date(year,month - 1,day);
                $log.info("in controller edit date updated: "+year);
                $log.info("in controller edit date updated: "+month);
                $log.info("in controller edit date updated: "+day);
                $log.info("in controller edit date updated: "+newDate);
                todo.dueDate = newDate;
            }
            $log.info(todo);
            $scope.newTodo=todo;
        };
        $scope.deleteById = function (id) {
            $log.info("in controller deleteById id "+id);
            todoService.deleteById(id)
                .then(populateView,onError);
        };

        $scope.setCompleted = function (id, completed) {
            $log.info("in controller setCompleted id "+id);
            $log.info("in controller setCompleted id "+id);
            todoService.setCompleted(id,completed)
                .then(populateView,onError);
        };

        $scope.cancel = function () {
            $log.info("in controller cancel");
            $scope.error = undefined;
            $scope.newTodo={};
            populateView();
        };

        populateView();
    };

    app.controller("TodoController",TodoController);

}());