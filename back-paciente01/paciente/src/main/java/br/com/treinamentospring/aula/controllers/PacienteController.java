package br.com.treinamentospring.aula.controllers;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import br.com.treinamentospring.aula.entities.Paciente;
import br.com.treinamentospring.aula.repositories.IPacienteRepository;
import br.com.treinamentospring.aula.requests.PacienteGetRequest;
import br.com.treinamentospring.aula.requests.PacientePostRequest;
import br.com.treinamentospring.aula.requests.PacientePutRequest;

import io.swagger.annotations.ApiOperation;

@Controller
@Transactional
public class PacienteController {
	
	@Autowired
	private IPacienteRepository pacientesRepository;
	
	//definido o endereço do serviço
	private static final String ENDPOINT = "api/paciente";
	
	//método para realizar o serviço de cadastro de paciente
	@ApiOperation("Serviço para cadastro de Paciente")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
	@CrossOrigin
	public ResponseEntity<String>post(@RequestBody PacientePostRequest request){
		try {
			Paciente p = new Paciente();
			p.setNome(request.getNome());
			p.setCpf(request.getCpf());
			p.setDataNascimento(request.getDataNascimento());
			p.setSexo(request.getSexo());
			
			
			
			return ResponseEntity.status(HttpStatus.OK).body("Paciente cadastrado com sucesso");
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERRO: "+e.getMessage());
		}
	}
	
	// Método para consultar os médicos
		@ApiOperation("Serviço para consulta de Médico")
		@RequestMapping(value= ENDPOINT, method = RequestMethod.GET)
		@CrossOrigin
		public ResponseEntity<List<PacienteGetRequest>> get() {
			
			
			List<PacienteGetRequest> response = new ArrayList<PacienteGetRequest>();
			
			for(Paciente p : pacientesRepository.findAll()) {
				
				PacienteGetRequest pc= new PacienteGetRequest();
				
				pc.setNome(p.getNome());
				pc.setCpf(p.getCpf());
				pc.setDataNascimento(p.getDataNascimento());
				pc.setSexo(p.getSexo());
				
				
				response.add(pc);
				
			}
			
			return ResponseEntity.status(HttpStatus.OK).body(response);
			
		}
		
		///////////////////////////////////////////////////////////////////////////
		
		// Método de consulta por id
		@ApiOperation("Serviço para consulta de Paciente através do Id")
		@RequestMapping(value= ENDPOINT + "/{idPaciente}", method = RequestMethod.GET)
		@CrossOrigin
		public ResponseEntity<PacienteGetRequest> getById(@PathVariable("idPaciente") Integer idPaciente) {
			
			Optional<Paciente> pc = pacientesRepository.findById(idPaciente);
			
			if(pc.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}else {
				PacienteGetRequest response = new PacienteGetRequest();
				
				Paciente p = pc.get();
				
				response.setIdPaciente(p.getIdPaciente());
				response.setNome(p.getNome());
				response.setCpf(p.getCpf());
				response.setDataNascimento(p.getDataNascimento());
				response.setSexo(p.getSexo());
				
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}
			
		}
		
		///////////////////////////////////////////////////////////////////////////
		
		// Método para exclusão do Médico
		@ApiOperation("Serviço para exclusão de Paciente")
		@RequestMapping(value= ENDPOINT + "/{idPaciente}", method = RequestMethod.DELETE)
		@CrossOrigin
		public ResponseEntity<String> delete (@PathVariable("idPaciente") Integer idPaciente) {
			
			try {
				
				Optional<Paciente> pc = pacientesRepository.findById(idPaciente);
				
				if(pc.isEmpty()) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Paciente não encontrado, verifique");
				}else {
					
					Paciente p = pc.get();
					
					pacientesRepository.delete(p);
					
					return ResponseEntity.status(HttpStatus.OK).body("Paciente excluido com sucesso.");
					
				}
			
				
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
			}
			
		}
		
		///////////////////////////////////////////////////////////////////////////
		
		// Método para atualização dos dados
		@ApiOperation("Serviço para atualizar dados do Paciente")
		@RequestMapping(value = ENDPOINT, method = RequestMethod.PUT)
		@CrossOrigin
		public ResponseEntity<String> put(@RequestBody PacientePutRequest request) {
			
			try {
				
				Optional<Paciente> item = pacientesRepository.findById(request.getIdPaciente());
				
				if(item.isEmpty()) {
					
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Paciente não encontrado.");
					
				} else {
					
					Paciente p = item.get();
					
					p.setNome(request.getNome());
					p.setCpf(request.getCpf());
					p.setDataNascimento(request.getDataNascimento());
					p.setSexo(request.getSexo());
					
					pacientesRepository.save(p);
					
					return ResponseEntity.status(HttpStatus.OK).body("Paciente atualizado com sucesso!");
					
				}
				
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
			}
			
		}

	}
	
	