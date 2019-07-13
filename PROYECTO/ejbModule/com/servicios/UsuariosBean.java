package com.servicios;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.entities.Usuario;
import com.entities.Rol;
import com.exception.ServiciosException;


@Stateless
public class UsuariosBean implements UsuariosBeanRemote {


	@PersistenceContext
	private EntityManager em;
	
    public UsuariosBean() {
    }
    @Override
    public void altaUsuario(Usuario usuario){
    	em.persist(usuario);
    	em.flush();
    }

	@Override
	public void altaUsuario(String nombre, String apellido, String tipo_documento, String documento,
			String mail, Date fecha_alta, Boolean status, Rol rol) throws ServiciosException {

		try{
			//verifica que el nombre no tenga mas de 45 caracteres
			if(nombre.length()>45){
				throw new ServiciosException("El nombre del usuario no puede tener mas de 45 caracteres");
			}
			if(apellido.length()>45){
				throw new ServiciosException("El apellido del usuario no puede tener mas de 45 caracteres");
			}
			Usuario usuario = new Usuario();
			usuario.setNombre(nombre);
			usuario.setApellido(apellido);
			usuario.setPasswordUsuario("inicial");
			usuario.setTipoDocumento(tipo_documento);
			usuario.setDocumento(documento);
			usuario.setMail(mail.trim().toLowerCase());
			usuario.setFechaAlta(fecha_alta);
			usuario.setFechaBaja(null);
			usuario.setStatus(status);
			usuario.setRolBean(rol);
			em.persist(usuario);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException(e.getMessage());
		}
	}
	
	@Override
	public void actualizarUsuario(Usuario usuario) throws ServiciosException {
		
		try{
			em.merge(usuario);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo actualizar el usuario");
		}
	}
	
	@Override
	public void deshabilitarUsuario(Usuario usuario) throws ServiciosException {

		try{
			
			usuario.setStatus(false);
			usuario.setFechaBaja(new Date());
			em.merge(usuario);

			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo deshabilitar el usuario");
		}
	}
	
	
	@Override
	public List<Usuario> obtenerTodos() {
		Query query = em.createQuery("SELECT u FROM Usuario u");
		return query.getResultList();
	}

	//el siguiente metodo valida si un email y contraseña son correctos
	@Override 	//recibe dos string mail y contraseña
	public boolean ValidarUsuario(String mail, String password) throws ServiciosException

	{try{
		//consulta en la base de datos por un usuario que tenga ese email y esa contraseña
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.mail LIKE :mail AND u.passwordUsuario LIKE :password AND status = TRUE ",Usuario.class)
				.setParameter("mail" , mail.trim().toLowerCase());
				query.setParameter("password", password.trim());//trim hace que se ignoren spacios
				
				
				if(query.getResultList().isEmpty()){//si la lista de resultados esta vacia devuelve falso
					return false;
				}
				else {
					return true;//si existe el usuario con ese email y esa contraseña devuelve verdadero 
				}
	}		
	catch (PersistenceException e) {
		// TODO: handle exception
	throw new ServiciosException("Ha ocurrido un error No se pudo validar el usuario");
	
	}			
	}
	@Override
	public Usuario ObtenerUsuario(String mail, String password) {
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.mail LIKE :mail AND u.passwordUsuario LIKE :password",Usuario.class)
				.setParameter("mail" , mail.trim().toLowerCase());
				query.setParameter("password", password.trim());
				
				return query.getResultList().get(0);
	}
	
	@Override
	public void modificarUsuario(String nombre, String apellido, String tipoDoc, String documento, String email, Date fecha,
			boolean status, Rol rol, Usuario usuario) throws ServiciosException {
try{
	
	
		usuario.setApellido(apellido);
		usuario.setNombre(nombre);
		usuario.setDocumento(documento);
		usuario.setFechaAlta(fecha);
		usuario.setStatus(status);
		usuario.setRolBean(rol);
		em.merge(usuario);

		em.flush();
		
}catch(PersistenceException e){
	throw new ServiciosException("No se pudo modificar Usuario");
}
		
		
		// TODO Auto-generated method stub
		
	}
	
	//el siguiente metodo cambia la contraseña de un usuario por inicial
	@Override
	public void resetearUsuario(Usuario usuario) throws ServiciosException {

		try{
			Usuario user = em.find(Usuario.class, usuario.getIdUsuario());
			usuario.setPasswordUsuario("inicial");
			em.merge(usuario);

			em.flush();
			
		}
		catch(PersistenceException e){
			throw new ServiciosException("No se pudo deshabilitar el usuario");
		}
	}
	
	//el siguiente metodo permite cambiar la contraseña d eun usuario
	@Override
	public void modificarContraseña(Usuario usuario, String password) throws ServiciosException {

		try{
			
			usuario.setPasswordUsuario(password);
			em.merge(usuario);
			em.flush();
			
		}
		catch(PersistenceException e){
			throw new ServiciosException("No se pudo modificar contraseña del usuario");
		}
	}
	
}
