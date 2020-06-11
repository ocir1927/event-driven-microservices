package com.costin.disertatie.accountprojection.query;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount,String> {
}
