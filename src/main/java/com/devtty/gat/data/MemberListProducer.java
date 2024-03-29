package com.devtty.gat.data;

import com.devtty.gat.model.Member;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@RequestScoped
public class MemberListProducer
{
   @Inject
   @MemberRepository
   private EntityManager em;

   private List<Member> members;

   // The @Named annotation allows us to access the return value via the EL variable name "member" in the UI (e.g., Facelets or JSP view)
   @Produces
   @Named
   public List<Member> getMembers()
   {
      return members;
   }

   public void onMemberListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Member member)
   {
      retrieveAllMembersOrderedByName();
   }

   @PostConstruct
   public void retrieveAllMembersOrderedByName()
   {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
      Root<Member> member = criteria.from(Member.class);
      // Uncomment if you would like to try out typesafe criteria queries, a new feature in JPA 2.0
      // criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
      criteria.select(member).orderBy(cb.asc(member.get("name")));
      members = em.createQuery(criteria).getResultList();
   }
}
