package com.devtty.gat.data;

import com.devtty.gat.model.Member;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TransactionRequiredException;
import javax.transaction.UserTransaction;
import org.slf4j.Logger;

/**
 * Import seed data into the database on application startup using
 * a startup singleton EJB.
 *
 * @author Dan Allen
 */
@Startup
@Singleton
public class SeedDataImporter
{
   @Inject
   private Logger log;
   
   @Inject
   @MemberRepository
   private EntityManager em;
   
   @Inject
   private UserTransaction tx;

   @PostConstruct
   public void importData()
   {
      Member member1 = new Member();
      member1.setName("John Smith");
      member1.setEmail("john.smith@mailinator.com");
      member1.setPhoneNumber("2125551212");
      try
      {
         try
         {
            em.persist(member1);
         }
         catch (TransactionRequiredException e)
         {
            // manual transaction control required in @PostConstruct method
            // only use if enforced by JPA provider (due to bug in GlassFish)
            tx.begin();
            em.persist(member1);
            tx.commit();
         }
         log.info("Successfully imported seed data.");
      }
      catch (Exception e)
      {
         log.warn("Seed data import failed.", e);
      }
   }
}
