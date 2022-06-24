package br.com.treinamentospring.aula.entities;

import javax.persistence.Column;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Table(name="Paciente")
@Entity
public class Paciente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idpaciente")
	
	private Integer IdPaciente;
	
	@Column(name = "nome", length = 150, nullable = false)
	private String nome;
	
	@Column(name = "cpf", length = 11, nullable = false)
	private String cpf;
	
	@Column(name = "dataNascimento", length = 8, nullable = false)
	private String dataNascimento;
	
	@Column(name = "sexo", length = 50, nullable = false)
	private String sexo;
	
	public Paciente() {
		// TODO Auto-generated constructor stub
	}

	public Integer getIdPaciente() {
		return IdPaciente;
	}

	public void setIdPaciente(Integer idPaciente) {
		IdPaciente = idPaciente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	@Override
	public String toString() {
		return "Paciente [IdPaciente=" + IdPaciente + ", nome=" + nome + ", cpf=" + cpf + ", dataNascimento="
				+ dataNascimento + ", sexo=" + sexo + "]";
	}
	
}
	




