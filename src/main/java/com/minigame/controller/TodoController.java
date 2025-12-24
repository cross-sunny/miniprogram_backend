package com.minigame.controller;

import com.minigame.entity.Todo;
import com.minigame.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/todo")
public class TodoController {
    @Autowired
    private TodoService todoService;

    // 新增任务
    @PostMapping("/add")
    public Map<String, Object> addTodo(@RequestBody Todo todo) {
        Map<String, Object> res = new HashMap<>();
        try {
            todoService.addTodo(todo);
            res.put("code", 200);
            res.put("msg", "新增任务成功");
        } catch (Exception e) {
            res.put("code", 500);
            res.put("msg", "新增失败：" + e.getMessage());
            e.printStackTrace();
        }
        return res;
    }

    // 查询用户的任务列表
    @GetMapping("/list")
    public Map<String, Object> getTodoList(@RequestParam Integer userId) {
        Map<String, Object> res = new HashMap<>();
        try {
            List<Todo> todoList = todoService.getTodoByUserId(userId);
            // 新增日志：打印查询到的任务数量和内容
            System.out.println("查询userId=" + userId + "的任务列表：");
            System.out.println("任务数量：" + (todoList == null ? 0 : todoList.size()));
            if (todoList != null && !todoList.isEmpty()) {
                for (Todo todo : todoList) {
                    System.out.println("任务ID：" + todo.getId() + "，内容：" + todo.getContent());
                }
            }
            res.put("code", 200);
            res.put("data", todoList);
        } catch (Exception e) {
            res.put("code", 500);
            res.put("msg", "查询失败：" + e.getMessage());
            e.printStackTrace();
        }
        return res;
    }

    // 更新任务完成状态
    @PutMapping("/updateStatus")
    public Map<String, Object> updateStatus(@RequestParam Integer id, @RequestParam Integer isDone) {
        Map<String, Object> res = new HashMap<>();
        try {
            todoService.updateTodoStatus(id, isDone);
            res.put("code", 200);
            res.put("msg", "状态更新成功");
        } catch (Exception e) {
            res.put("code", 500);
            res.put("msg", "更新失败：" + e.getMessage());
            e.printStackTrace();
        }
        return res;
    }

    // 删除任务
    @DeleteMapping("/delete")
    public Map<String, Object> deleteTodo(@RequestParam Integer id) {
        Map<String, Object> res = new HashMap<>();
        try {
            todoService.deleteTodo(id);
            res.put("code", 200);
            res.put("msg", "删除任务成功");
        } catch (Exception e) {
            res.put("code", 500);
            res.put("msg", "删除失败：" + e.getMessage());
            e.printStackTrace();
        }
        return res;
    }
}