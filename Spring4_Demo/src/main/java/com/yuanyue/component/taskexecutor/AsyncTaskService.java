package com.yuanyue.component.taskexecutor;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncTaskService {

	@Async
	public void executeAsyncTask(Integer i) {
		System.out.println("ִ���첽����:"+i);
	}
	
	@Async
	public void executeAsyncTaskPlus(Integer i) {
		System.out.println("ִ���첽����+1:"+(i+i));
	}
	
	@Async
	public void executeAsyncTaskRecheck(Integer i) {
		System.out.println("����첽�����Ƿ�������"+i);
	}
}
