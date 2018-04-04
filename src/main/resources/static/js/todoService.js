
(function () {
    var API = "/todos";

    var todoService = function ($http,$log) {

        var findAll = function () {
            $log.info("in service findAll");
            return $http.get(API)
                .then(function (response) {
                    return response.data;
                });
        };

        var addTodo = function (newTodo) {
            $log.info("in service addTodo");
            $log.info(newTodo);
            return $http.post(API,newTodo)
                .then(function (response) {
                    return response.data;
                });
        };

        var deleteById = function (id) {
            $log.info("in service deleteById: "+id);
            return $http.delete(API+"/"+id)
                .then(function (response) {
                    return response.data;
                });
        };

        var setCompleted = function (id,completed) {
            $log.info("in service setCompleted: "+id);
            $log.info("in service setCompleted: "+completed);
            return $http.put(API+"/"+id+"/"+completed)
                .then(function (response) {
                    return response.data;
                });
        };

        return {
            findAll: findAll,
            addTodo:addTodo,
            deleteById:deleteById,
            setCompleted:setCompleted
        };

    };

    var module = angular.module("todo");
    module.factory("todoService",todoService);
}());