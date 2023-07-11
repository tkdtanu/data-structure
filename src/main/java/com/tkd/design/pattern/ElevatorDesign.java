package com.tkd.design.pattern;

import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.collections4.CollectionUtils;

import lombok.AllArgsConstructor;
import lombok.Data;

public class ElevatorDesign {
	enum Direction {
		UP, DOWN
	}

	@Data
	@AllArgsConstructor
	static class FloorRequest {
		int sourceFloor;
		Direction directionToGo;
	}

	@Data
	@AllArgsConstructor
	static class LiftRequest {
		int destinationFloor;
	}

	@Data
	@AllArgsConstructor
	static class Request implements Comparable<Request> {
		FloorRequest floorRequest;
		LiftRequest liftRequest;

		public int compareTo(Request req) {
			if (this.getLiftRequest().getDestinationFloor() == req.getLiftRequest().getDestinationFloor()) {
				return 0;
			} else if (this.getLiftRequest().getDestinationFloor() > req.getLiftRequest().getDestinationFloor()) {
				return 1;
			} else {
				return -1;
			}
		}
	}

	enum State {
		MOVING, STOPPED, IDLE
	}

	@Data
	static class Elevator {
		int currentFloor = 0;
		Direction currentDirection = Direction.UP;
		State currentState = State.IDLE;
		private TreeSet<Request> currentRequests = new TreeSet<ElevatorDesign.Request>();
		private TreeSet<Request> upwardPendingRequests = new TreeSet<ElevatorDesign.Request>();
		private TreeSet<Request> downwardPendingRequests = new TreeSet<ElevatorDesign.Request>();

		public void startElevator() {
			while (true) {
				if (CollectionUtils.isNotEmpty(currentRequests)) {
					if (currentDirection == Direction.UP) {
						processUpRequest(currentRequests.pollFirst());
						if (currentRequests.isEmpty()) {
							addPendingDownwardJobsToCurrentJobs();
						}
					}
					if (currentDirection == Direction.DOWN) {
						processDownRequest(currentRequests.pollLast());
						if (currentRequests.isEmpty()) {
							addPendingUpwardJobsToCurrentJobs();
						}
					}
				}
			}
		}

		private void addPendingUpwardJobsToCurrentJobs() {
			if (!upwardPendingRequests.isEmpty()) {
				currentRequests = upwardPendingRequests;
				currentDirection = Direction.UP;
			} else {
				currentState = State.IDLE;
				System.out.println("The Elevator is in Idle state");
			}

		}

		private void addPendingDownwardJobsToCurrentJobs() {
			if (!downwardPendingRequests.isEmpty()) {
				currentRequests = downwardPendingRequests;
				currentDirection = Direction.DOWN;
			} else {
				currentState = State.IDLE;
				System.out.println("The Elevator is in Idle State");
			}
		}

		private void processDownRequest(Request pollLast) {
			// TODO Auto-generated method stub

		}

		private void processUpRequest(Request pollFirst) {
			// TODO Auto-generated method stub

		}

	}

	public static void main(String[] args) {

		Elevator elevator = new Elevator();
		System.out.println(Runtime.getRuntime().availableProcessors());
		ExecutorService service = Executors.newCachedThreadPool();

	}
}
