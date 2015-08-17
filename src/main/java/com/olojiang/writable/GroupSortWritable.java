package com.olojiang.writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.WritableComparable;

public class GroupSortWritable implements Serializable, WritableComparable<GroupSortWritable> {
	private static final long serialVersionUID = 1158102272972243538L;
	private IntWritable institute;
	private FloatWritable grade;

	public GroupSortWritable(int institute, float grade) {
		this.institute = new IntWritable(institute);
		this.grade = new FloatWritable(grade);
	}
	
	/**
	 * This constructor is one of the key method for serialize
	 */
	public GroupSortWritable() {
		this.setInstitute(new IntWritable());
		this.setGrade(new FloatWritable());
	}
	
	public IntWritable getInstitute() {
		return institute;
	}

	public void setInstitute(IntWritable institute) {
		this.institute = institute;
	}

	public FloatWritable getGrade() {
		return grade;
	}

	public void setGrade(FloatWritable grade) {
		this.grade = grade;
	}

	public void readFields(DataInput input) throws IOException {
		institute.readFields(input);
		grade.readFields(input);
	}

	public void write(DataOutput output) throws IOException {
		institute.write(output);
		grade.write(output);
	}

	public int compareTo(GroupSortWritable o) {
		int result = this.institute.compareTo(o.institute);
		if( result == 0) {
			return this.grade.compareTo(o.grade);
		} else {
			return result;
		}
	}
	
	public int hashCode() {
		return this.institute.hashCode() + this.grade.hashCode();
	}
	
	public boolean equals(Object o) {
		if( o instanceof GroupSortWritable ) {
			GroupSortWritable other = (GroupSortWritable)o;
			return this.institute.equals(other.institute)&& this.grade.equals(other.grade); 
		} else {
			return false;
		}
	}

	public String toString() {
		return this.institute.toString() + "\t" + this.grade.toString();
	}
}
