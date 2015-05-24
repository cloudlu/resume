package com.lytx.finance.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name = "user")
@NamedQueries({
    @NamedQuery(
            name = "findUserByName",
            query = "select r from User r where r.username = :name "
    )
})
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2989448605305814612L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Basic(optional = false)
	@Column(nullable = false, length = 50)
	@NotNull
	@NotBlank
    @Length(min = 4, max = 50)
	private String realname;
	@Basic(optional = false)
	@Column(nullable = false, name = "name", length = 50, unique = true)
	@NotNull
	@NotBlank
	//@Pattern(regexp = "[A-Za-z0-9]{4,50}", message = "{username.illegal}")
    @Length(min = 4, max = 50)
	private String username;
	@Basic(optional = false)
	@Column(nullable = false, length = 150)
	@NotNull
	@NotBlank
    @Length(min = 4, max = 150)
	private String password;
	@Basic(optional = false)
	@Column(nullable = false)
	private String passwordHint;
	@Basic(optional = false)
	@Column(nullable = false, length = 50, unique = true)
	@Email
	@Length(min = 1, max = 50)
	private String email;
	@Basic(optional = false)
	@Column(nullable = false, length = 20)
	private String phoneNumber;
	@Basic(optional = false)
	@Column(nullable = false)
	private Date registerTime;
	
	@Basic(optional = false)
	@Column(nullable = false, name="accountEnabled")
	private boolean enabled;
	@Basic(optional = false)
	@Column(nullable = false)
	private boolean accountExpired;
	@Basic(optional = false)
	@Column(nullable = false)
	private boolean accountLocked;
	@Basic(optional = false)
	@Column(nullable = false)
	private boolean credentialsExpired;
	@Basic(optional = false)
	@Column(nullable = false)
	private Date expiredTime;
	@Basic(optional = false)
	@Column(nullable = false)
	private String credentialsSalt;

	@Transient
	private String confirmPassword;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST/* , CascadeType.MERGE */ })
    @JoinTable(
            name = "user_role",
            joinColumns = { @JoinColumn(name = "user_id",  referencedColumnName="id") },
            inverseJoinColumns = @JoinColumn(name = "role_id",  referencedColumnName="id")
    )
	private Set<Role> roles = new HashSet<Role>();

	@Version
	private Integer version;
	
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public User() {

	}
	
	public User(Integer id, String realname, String username,
			String password, String passwordHint, String email,
			String phoneNumber, Date registerTime, boolean enabled,
			boolean accountExpired, boolean accountLocked,
			boolean credentialsExpired, Date expiredTime) {
		super();
		this.id = id;
		this.realname = realname;
		this.username = username;
		this.password = password;
		this.passwordHint = passwordHint;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.registerTime = registerTime;
		this.enabled = enabled;
		this.accountExpired = accountExpired;
		this.accountLocked = accountLocked;
		this.credentialsExpired = credentialsExpired;
		this.expiredTime = expiredTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String name) {
		this.username = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getPasswordHint() {
		return passwordHint;
	}

	public void setPasswordHint(String passwordHint) {
		this.passwordHint = passwordHint;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Role> getRoles() {
		
		/*if(roles.isEmpty()){
			if(null == branch)
				roles.add(new Role("ROLE_ANONYMOUS"));
			if("ALL".equalsIgnoreCase(branch)){
				roles.add(new Role("ROLE_ADMIN"));
			} else
				roles.add(new Role("ROLE_USER"));			
		}*/
		
		return roles;
	}

	/**
	 * Adds a role for the user
	 * 
	 * @param role
	 *            the fully instantiated role
	 */
	public void addRole(Role role) {
		getRoles().add(role);
	}
	
	/**
	 * Adds a role for the user
	 * 
	 * @param role
	 *            the fully instantiated role
	 */
	public boolean removeRole(Role role) {
		return getRoles().remove(role);
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Date getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}

	public boolean isCredentialsExpired() {
		return credentialsExpired;
	}

	public boolean isAccountExpired() {
		return accountExpired;
	}

	public boolean isAccountLocked() {
		return accountLocked;
	}

	public boolean isAccountNonExpired() {
		return !accountExpired;
	}

	public boolean isAccountNonLocked() {
		return !accountLocked;
	}

	public boolean isCredentialsNonExpired() {
		return !credentialsExpired;
	}

	public boolean isEnabled() {
		return enabled;
	}	
	
	/**
	 * {@inheritDoc}
	 */
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof User)) {
			return false;
		}

		final User user = (User) o;

		return !(username != null ? !username.equals(user.getUsername()) : user
				.getUsername() != null);

	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return (username != null ? username.hashCode() : 0);
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getClass().getCanonicalName()).append(" username: ")
				.append(this.username).append(" enabled: ").append(this.enabled)
				.append(" accountExpired: ").append(this.accountExpired)
				.append(" credentialsExpired: ").append(this.credentialsExpired)
				.append(" accountLocked: ").append(this.accountLocked);

		if (null != roles) {
			sb.append(" Granted Authorities: ");

			int i = 0;
			for (Role role : roles) {
				if (i > 0) {
					sb.append(", ");
				}
				sb.append(role.toString());
				i++;
			}
		} else {
			sb.append(" No Granted Authorities");
		}
		return sb.toString();
	}

	public String getCredentialsSalt() {
		return credentialsSalt;
	}
	
	public void setCredentialsSalt(String credentialsSalt) {
		this.credentialsSalt = credentialsSalt;
	}

}
