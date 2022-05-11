package com.example.mygson.bean;

public class Job {
    private String jobName;
    private  double salary;

    public Job() {
    }

    public Job(String jobName, double salary) {
        this.jobName = jobName;
        this.salary = salary;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobName='" + jobName + '\'' +
                ", salary=" + salary +
                '}';
    }
}
