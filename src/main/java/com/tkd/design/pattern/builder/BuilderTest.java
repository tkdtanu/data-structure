package com.tkd.design.pattern.builder;

public class BuilderTest {
	public static class Employee {
		private int id;
		private String name;
		private String department;

		private Employee(Builder builder) {
			this.id = builder.id;
			this.name = builder.name;
			this.department = builder.department;
		}

		private static class Builder {
			private int id;
			private String name;
			private String department;

			public Builder id(int id) {
				this.id = id;
				return this;
			}

			public Builder name(String name) {
				this.name = name;
				return this;
			}

			public Builder department(String department) {
				this.department = department;
				return this;
			}

			public Employee build() {
				return new Employee(this);
			}

		}

		public static Builder builder() {
			return new Builder();
		}

		public int getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public String getDepartment() {
			return department;
		}

		@Override
		public String toString() {
			return "Employee [id=" + id + ", name=" + name + ", department=" + department + "]";
		}

	}

	public static void main(String[] args) {
		Employee e = Employee.builder().id(0).name("Tkd").department("IT").build();
		System.out.println(e);
		// We use Builder Design pattern when the object creation can happen in multiple
		// ways
		// Other Examples -> RequestEntityBuilder, Marketplace Order Payload Builder,
		// S3ClientBuilder, DynamoDbClientBuilder
	}
}
