package org.example.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.example.mapper.OperateLogMapper;
import org.example.pojo.OperateLog;
import org.example.utils.CurrentHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Slf4j
@Component
public class OperationLogAspect {
    @Autowired
    OperateLogMapper operateLogMapper;

    //操作人、操作时间、执行方法的全类名、执行方法名、方法运行时参数、返回值、方法执行时长
    @Around("@annotation(org.example.anno.LogOperation)")
    public Object logOperation(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取操作人ID
        Integer operateEmpId =getCurrentUserId();
        // 记录操作时间
        LocalDateTime operateTime = LocalDateTime.now();
        // 获取执行方法的全类名
        String className = joinPoint.getTarget().getClass().getName();
        // 获取执行方法名
        String methodName = joinPoint.getSignature().getName();
        // 获取方法运行时参数
        Object[] methodParams = joinPoint.getArgs();

        //记录方法开始时间
        long start = System.currentTimeMillis();
        //获取返回值
        Object returnValue;
        try {
            returnValue = joinPoint.proceed();
        } catch (Throwable throwable) {
            //若方法执行抛出异常，也记录相关信息（可选，根据需求决定是否处理）
            log.error("方法执行异常，类：{}，方法：{}，参数：{}", className, methodName, methodParams, throwable);
            throw throwable;
        }
        //记录方法结束时间
        long end = System.currentTimeMillis();
        //获取方法执行时长
        long costTime=end-start;

        //赋值给bean
        OperateLog operateLog = new OperateLog();
        operateLog.setOperateEmpId(operateEmpId);
        operateLog.setOperateTime(operateTime);
        operateLog.setClassName(className);
        operateLog.setMethodName(methodName);
        operateLog.setMethodParams(Arrays.toString(methodParams));
        operateLog.setReturnValue(returnValue.toString());
        operateLog.setCostTime(costTime);
        //记录日志
        log.info("操作人ID：{}，操作时间：{}，执行类：{}，执行方法：{}，方法参数：{}，返回值：{}，执行时长：{}ms",
                operateEmpId,operateTime, className, methodName, methodParams, returnValue, costTime);
        //向数据库中插入数据
        operateLogMapper.insert(operateLog);
        return returnValue;
    }


    // 示例方法，获取当前用户ID
    /*private int getCurrentUserId() {
        return CurrentHolder.getCurrentId();
    }*/
    public int getCurrentUserId(){
        return CurrentHolder.getCurrentId();
    }
}

