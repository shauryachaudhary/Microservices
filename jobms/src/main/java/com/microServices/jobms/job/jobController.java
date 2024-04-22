package com.microServices.jobms.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs") /*it will set base url for all the methods in the particular controller.*/
public class jobController {
    private JobService jobService;

    public jobController(JobService jobService) {
        this.jobService = jobService;
    }

    //@GetMapping("/jobs")
    @GetMapping
    public ResponseEntity<List<Job>> findAll(){
        return ResponseEntity.ok(jobService.findAll());
    }
    //@PostMapping("/jobs")
    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);

        return new ResponseEntity<>("Job Added",HttpStatus.OK);
    }
    //@GetMapping("/jobs/{id}")
    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){ //@PathVariable assign the value to {id}.
        Job job = jobService.getJobById(id);
        if(job != null) return new ResponseEntity<>(job, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id){
         boolean deleted =  jobService.deleteJobById(id);
          if(deleted){
              return new ResponseEntity<>("Job Deleted",HttpStatus.OK);

          }
          return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{id}") /*It is specilized version of requestMapping. and It also reduces code.*/
    //@RequestMapping(value = "/jobs/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateJobById(@PathVariable Long id, @RequestBody Job updatedJob){
        boolean updated = jobService.updateJobById(id, updatedJob);
        if(updated)return new ResponseEntity<>("Job Updated",HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

/*
* GET/jobs : Get all jobs
* GET/jobs/{id} : Get a specific job by ID
* POST/jobs : Create a new job (request body should contain the job details)
* DELETE/jobs/{id} : Delete a specific job by ID.
* PUT/jobs/{id}: Update a specific job by ID
* GET/jobs/{id}/company: Get the company associated with a specific job by ID.
*
*Example API URLs:
* GET {base_url}/jobs
*
* PUT {base_url}/jobs/1
* */
