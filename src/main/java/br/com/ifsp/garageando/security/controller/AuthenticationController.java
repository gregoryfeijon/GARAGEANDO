package br.com.ifsp.garageando.security.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *	6 de nov de 2019
 *
 *	@author gregory.feijon
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping
public class AuthenticationController {
	
}
