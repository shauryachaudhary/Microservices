package com.microServices.jobms.job.implementation;

import com.microServices.jobms.job.Job;
import com.microServices.jobms.job.JobRepository;
import com.microServices.jobms.job.JobService;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {


    //private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;


    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        //jobRepository.save();
        return jobRepository.findAll();
    }




    @Override
    public void createJob(Job job) {

        jobRepository.save(job);

    }

    @Override
    public Job getJobById(Long id) {
       return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        /*Iterator<Job> iterator = jobs.iterator();
        while(iterator.hasNext()){
            Job job = iterator.next();
            if(job.getId().equals(id)){
                iterator.remove();
                return true;
            }
        }
        */
        try {
            jobRepository.deleteById(id);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean updateJobById(Long id, Job UpdatedJob ) {
        Optional<Job> jobOptional = jobRepository.findById(id);

            if(jobOptional.isPresent()){
                Job job = jobOptional.get();
                job.setTitle(UpdatedJob.getTitle());
                job.setDescription(UpdatedJob.getDescription());
                job.setMaxSalary(UpdatedJob.getMaxSalary());
                job.setMinSalary(UpdatedJob.getMinSalary());
                job.setLocation(UpdatedJob.getLocation());
                jobRepository.save(job);
                return true;
            }

        return false;
    }
}
