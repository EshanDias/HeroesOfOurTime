package com.assignments.sliit.heroesofourtime.dbAccess;

import android.content.Context;

import com.assignments.sliit.heroesofourtime.R;
import com.assignments.sliit.heroesofourtime.model.Hero;
import com.assignments.sliit.heroesofourtime.model.User;
import com.assignments.sliit.heroesofourtime.model.UserHero;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by esh_d on 27/03/2018.
 * Initial Data
 */

public class InitialData {

    InitialData(Context context) {
    }

    //region Heroes Data
    public List<Hero> getHeroes() {
        List<Hero> heroList = new ArrayList<>();
        Hero hero;
        Calendar cal = Calendar.getInstance();

        //region INSERT HEROES
        //1
        hero = new Hero();
        hero.setName("Nelson Mandela");
        cal.set(1918,Calendar.JULY,18);
        hero.setBirthday(cal.getTime());
        cal.set(2013,Calendar.DECEMBER,5);
        hero.setDeath(cal.getTime());
        hero.setSummary("South Africa’s first elected President");
        hero.setDescription("Nelson Mandela was South Africa’s first elected President in a democratic election from 1994-99. He also held the Secretary General post of the Non-Aligned Movement during 1998-99. Mandela was also a Philanthropist and he aimed to tackle poverty and HIV/AIDS. Mandela was leading a campaign against the then apartheid government, for which he was sentenced to life imprisonment and served over 27 years in prison (1962-90). He was accredited internationally for his anti-apartheid revolution and received numerous honors including Presidential Medal of Freedom (U.S.A), Order of Lenin (Russia), Bharat Ratna (India) and the most notable Nobel Peace Prize. He is often described as “the father of the nation” by the South Africans.");
        hero.setHeroImage(R.drawable.nelson_mandela);
        hero.setCreatedDate(Calendar.getInstance().getTime());
        hero.setModifiedDate(Calendar.getInstance().getTime());

        heroList.add(hero);

        //2
        hero = new Hero();
        hero.setName("Stephen Hawking");
        cal.set(1918,Calendar.JANUARY,8);
        hero.setBirthday(cal.getTime());
        cal.set(2018,Calendar.MARCH,14);
        hero.setDeath(cal.getTime());
        hero.setSummary("Scientist in the field of Theoretical Physics and Cosmology");
        hero.setDescription("Stephen Hawking is an imminent scientist in the field of  Theoretical Physics and Cosmology has worked on the basic laws which govern the universe and was the first to state that black holes emit radiations (often termed as Hawkings Radiation) after he unified the General Relativity with Quantum Theory. He has been confined to wheelchair and dependent on a computerized voice system for communication for most of his life after being diagnosed with ALS (a form of Motor Neuron Disease) at an age of 21. Despite all these setbacks he has achieved many things which most others can only wish to do in their dreams. Hawking has been conferred with 12 honorary degrees and has been awarded CBE (UK), and he is also a recipient of the Presidential Medal of Freedom (USA) to name a few.");
        hero.setHeroImage(R.drawable.stephen_hawking);
        hero.setCreatedDate(Calendar.getInstance().getTime());
        hero.setModifiedDate(Calendar.getInstance().getTime());

        heroList.add(hero);

        //3
        hero = new Hero();
        hero.setName("Abdul Kalam");
        cal.set(1931,Calendar.OCTOBER,15);
        hero.setBirthday(cal.getTime());
//        cal.set(2013,12,5);
        hero.setDeath(null);
        hero.setSummary("Indian scientist who also served as the President of India");
        hero.setDescription("A.P.J Abdul Kalam is an Indian scientist who also served as the President of India from 2002-07. Kalam worked at an early age to support his studies and also to aid his family’s low income.While at ISRO he developed India’s first indigenous Satellite Vehicle (SLV) and was mainly responsible for the development of ISRO’s launch vehicles which includes the present generations PSLV. After his tenure at ISRO, Kalam took up responsibility of developing Indigenous Guided Missiles at DRDO, where he was responsible for the development of the famous PRITHVI and AGNI Missiles.For his work in the field of aerospace engineering he has been conferred with many honorary doctorates and fellow of many institutions. He has been awarded the highest civilian award of Bharat Ratna by the Government of India.");
        hero.setHeroImage(R.drawable.abdul_kalam);
        hero.setCreatedDate(Calendar.getInstance().getTime());
        hero.setModifiedDate(Calendar.getInstance().getTime());

        heroList.add(hero);

        //4
        hero = new Hero();
        hero.setName("Steve Jobs");
        cal.set(1955,Calendar.FEBRUARY,29);
        hero.setBirthday(cal.getTime());
        cal.set(2011,Calendar.OCTOBER,5);
        hero.setDeath(cal.getTime());
        hero.setSummary("Co-founder of the Apple Inc.");
        hero.setDescription("Steve Jobs was co-founder of the famous Apple Inc. He was also the founder of Pixar an animation company when he was sent out of his own company (Apple Inc.) and produced some of the best animated stories till date. He not just founded Apple but also rebuilt it after his return to Apple when it was in shambles. On his return to Apple he just made a path of success is redefining technology by introducing iPod, iPhone and iPad. He has been showered with loads of awards during his lifetime and posthumously. He was awarded Grammy Trustees Award (award given to those who have influenced music industry), inducted as a Disney Legend. Jobs was named as the CEO of the Decade by Fortune magazine. For his influence in technology he has been referred as a “visionary” and has been described as “Father of Digital Revolution”.");
        hero.setHeroImage(R.drawable.steve_jobs);
        hero.setCreatedDate(Calendar.getInstance().getTime());
        hero.setModifiedDate(Calendar.getInstance().getTime());

        heroList.add(hero);

        //5
        hero = new Hero();
        hero.setName("Oprah Winfrey");
        cal.set(1954,Calendar.JANUARY,29);
        hero.setBirthday(cal.getTime());
        hero.setDeath(null);
        hero.setSummary("The Oprah Winfrey Show");
        hero.setDescription("Oprah Winfrey best known for her talk show “The Oprah Winfrey Show”. Her way to the top has come after overcoming hardship during her childhood when she was raped at an age of 9 & became pregnant by 14. It was because of her show that confessions became popular, during her show she sometimes interviewed celebrities and once she interviewed Michael Jackson which is most watched interview ever. She has been rated as the most influential women in the world and because of her support to the then presidential candidate Barack Obama delivered him over 1 million votes. She is also the richest self-made billionaire women. For her work she was awarded the Presidential Medal of Freedom (U.S.A) by President Barack Obama.");
        hero.setHeroImage(R.drawable.oprah_winfrey);
        hero.setCreatedDate(Calendar.getInstance().getTime());
        hero.setModifiedDate(Calendar.getInstance().getTime());

        heroList.add(hero);

        //6
        hero = new Hero();
        hero.setName("Warren Buffett");
        cal.set(1930,Calendar.AUGUST,30);
        hero.setBirthday(cal.getTime());
        hero.setDeath(null);
        hero.setSummary("Wizard of Omaha");
        hero.setDescription("Warren Buffett is a business magnate and investor, also known as “Wizard of Omaha”, Omaha being his birth place. He is presently the Chairman and CEO of Berkshire Hathaway. Buffett sounded like a businessman right from his childhood and was able to create a ladder of success to reach the zenith. He doesn’t believe in inheritance and so that his children become successful on their own and not by any support. He is widely considered as the most successful investor of the 20th century. He is the ranked among the world richest persons and most influential people in the world. Even though he is mighty rich but still he doesn’t waste his money and has pledged to donate about 99% of his wealth through Buffett Foundation and Gates & Melinda Foundation. He was conferred with Presidential Medal of Freedom (U.S.A) by Barack Obama in 2011.");
        hero.setHeroImage(R.drawable.warren_buffett);
        hero.setCreatedDate(Calendar.getInstance().getTime());
        hero.setModifiedDate(Calendar.getInstance().getTime());

        heroList.add(hero);

        //7
        hero = new Hero();
        hero.setName("Bill Gates");
        cal.set(1955,Calendar.OCTOBER,28);
        hero.setBirthday(cal.getTime());
        hero.setDeath(null);
        hero.setSummary("Bill Gates is the co-founder of the renowned Microsoft");
        hero.setDescription("Bill Gates is the co-founder of the renowned Microsoft, which is ever present in the Fortune 500 List thereby making him one of the richest persons in the World. He has been the architect of Windows OS and MS-Office, which took the whole world by storm. He also established his own charitable trust along with his wife in Bill & Melinda Gates Foundation. Gates has donated large chunks of his money in research to tackle the present day problems in Health & Education.Gates has received many honorary doctorates from many leading institutions across the world for his contribution. Bill Gates was made an honorary KBE (UK) by Queen Elizabeth II in 2005. He along with his wife has pledged to donate about 95% of their wealth to charity.");
        hero.setHeroImage(R.drawable.bill_gates);
        hero.setCreatedDate(Calendar.getInstance().getTime());
        hero.setModifiedDate(Calendar.getInstance().getTime());

        heroList.add(hero);

        //8
        hero = new Hero();
        hero.setName("Pele");
        cal.set(1940,Calendar.OCTOBER,23);
        hero.setBirthday(cal.getTime());
        hero.setDeath(null);
        hero.setSummary("Former Footballer");
        hero.setDescription("Edson Arantes do Nascimento or often called as Pele was a former Footballer who won 3 world cup titles with the Brazilian National Team. He not only won accolades with the country but also with his club Santos to which he served for nearly 19 years. Pele was so dominant of his era that he has scored around 1281 goals in 1363 games which is a Guinness World Record.In Brazil where Football is everything, Pele is considered as a national hero. He was named as the “World Player of the Century” by IFFHS and was elected as the “Athlete of the Century” by IOC. Pele also supports to improve the social conditions of the poor and one such example is when he dedicated his 1,000th goal to the poor children of Brazil.");
        hero.setHeroImage(R.drawable.pele);
        hero.setCreatedDate(Calendar.getInstance().getTime());
        hero.setModifiedDate(Calendar.getInstance().getTime());

        heroList.add(hero);

        //9
        hero = new Hero();
        hero.setName("Angela Merkel");
        cal.set(1954,Calendar.JULY,7);
        hero.setBirthday(cal.getTime());
        hero.setDeath(null);
        hero.setSummary("Chancellor of Germany");
        hero.setDescription("Angela Merkel is the current chancellor of Germany and has retained her post since 2005, making her the most successful elected female politician. Merkel was initially a physical chemist but she entered politics in the wake of the 1989 revolutions i.e. when the East and West Germany Unified.She also held the post as President of European Council. Merkel had a major role in managing the financial crisis at the both European and International Levels. Angela Merkel has been honored with Order of Merit from Italy, Germany and Norway Governments respectively, along with these she had been also awarded the Presidential Medal of Freedom (U.S.A). She also featured in the list of the most powerful people of the world by Forbes.");
        hero.setHeroImage(R.drawable.angela_merkel);
        hero.setCreatedDate(Calendar.getInstance().getTime());
        hero.setModifiedDate(Calendar.getInstance().getTime());

        heroList.add(hero);

        //10
        hero = new Hero();
        hero.setName("Mark Zuckerberg");
        cal.set(1984,Calendar.MAY,14);
        hero.setBirthday(cal.getTime());
        hero.setDeath(null);
        hero.setSummary("Mark Zuckerberg is the reason for Internet boom");
        hero.setDescription("He is one of the co-founder of the famous Social Networking website Facebook. He created the site with his friends in his college dorm, and then in order to concentrate more on the website he left Harvard University.Since then he has just rose to prominence and now and there are more than 1 billion users on Facebook indicating its success story. The success of Facebook made Mark the youngest CEO in the Fortune 500 List. Although amassing a good fortune, Zukerberg has donated his millions to fund various philanthropic causes. He has also pledged to donate at least 50% of his wealth to charity in his lifetime.");
        hero.setHeroImage(R.drawable.mark_zuckerberg);
        hero.setCreatedDate(Calendar.getInstance().getTime());
        hero.setModifiedDate(Calendar.getInstance().getTime());

        heroList.add(hero);

        //endregion

        return heroList;
    }

    //endregion

    //region User Data
    public List<User> getInitialUsers(){
        List<User> userList = new ArrayList<>();
        User user;

        //1
        user = new User();
        user.setName("Eshan Dias");
        user.setEmail("eash_di@ymail.com");
        user.setPassword("123456789");
        user.setHint("123456789");

        userList.add(user);

        //2
        user = new User();
        user.setName("Pooja Withanage");
        user.setEmail("pwithanage@gmail.com");
        user.setPassword("123456789");
        user.setHint("123456789");

        userList.add(user);

        return userList;
    }
    //endregion
}
