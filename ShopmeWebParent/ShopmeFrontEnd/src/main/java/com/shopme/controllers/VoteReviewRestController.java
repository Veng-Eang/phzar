package com.shopme.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopme.common.entity.Customer;
import com.shopme.constant.VoteType;
import com.shopme.payloads.VoteResult;
import com.shopme.services.ReviewVoteService;

@RestController
public class VoteReviewRestController {

	@Autowired private ReviewVoteService service;
	@Autowired private ControllerHelper helper;
	
	@PostMapping("/vote_review/{id}/{type}")
	public VoteResult voteReview(@PathVariable(name = "id") Integer reviewId,
			@PathVariable(name = "type") String type,
			HttpServletRequest request) {
		
		Customer customer = helper.getAuthenticatedCustomer(request);
		
		if (customer == null) {
			return VoteResult.fail("You must login to vote the review.");
		}
		
		VoteType voteType = VoteType.valueOf(type.toUpperCase());
		return service.doVote(reviewId, customer, voteType);
	}
}
