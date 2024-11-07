package myPharm.myPharm.service;

import myPharm.myPharm.domain.entity.IngredientEntity;
import myPharm.myPharm.domain.entity.MedboxEntity;

import java.util.*;

public class InterationCheckServiceImpl implements InteractionCheckSercive{
    public List<String[]> checkInteraction(List<MedboxEntity> medboxEntities){
//        Set<String> medIngredient = new HashSet<>();
//        List<String[]> alertList = new ArrayList<>();
//        //medbox약이름 성분으로 변경
//        List<IngredientEntity> list = new ArrayList<>();
//
//        for(IngredientEntity ingredient: list){
//
//            medIngredient.add(ingredient.getIngredientName());
//        }
//        //med의 금지 받아옴
//        //List<IngredientEntity> against = new ArrayList<>();


        List<String[]> alertList = new ArrayList<>();//리턴값
        Map<String,String> medIngre = new HashMap();//성분 /약이름// ingredient테이블 medicine테이블 조인해서 가져옴 약기준으로 성분가져와서 반대로넣기

        Map<String,String> againstIngre = new HashMap();//안되는성분 /약성분//릴레이션테이블에서 가져옴
        for(MedboxEntity med:medboxEntities){
            //med의 금지 받아옴
            List<IngredientEntity> ingres = new ArrayList<>();
            for(IngredientEntity in:ingres) {
                medIngre.put(in.getIngredientName(), med.getMedicine().getMedicineName());
                //안되는거 뽑아옴
                List<IngredientEntity> against = new ArrayList<>();//in기준
                for(IngredientEntity aga :against){
                    againstIngre.put(aga.getIngredientName(),in.getIngredientName());
                }
            }
            for(String key:medIngre.keySet()){
                if(againstIngre.containsKey(key)){

                    alertList.add(new String[]{medIngre.get(againstIngre.get(key)), medIngre.get(key)});
                }
            }

        }





        return alertList;

    }
}
