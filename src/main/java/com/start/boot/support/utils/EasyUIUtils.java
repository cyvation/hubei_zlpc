package com.start.boot.support.utils;

import com.start.boot.support.structure.EasyUITree;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * EasyUI封装
 * Created by lenovo on 2017/10/25.
 */
@Component
public class EasyUIUtils<T> {
    private final String JSON_NULL = "[]";

    public EasyUIUtils() { }

    /**
     * 转换List<Map>中数据为EasyUI下拉框控件数据源JSON
     *
     * @param list
     *            数据源
     * @param colID
     *            ID列
     * @param colName
     *            显示列
     * @return JSON字符串
     */
    @SuppressWarnings("rawtypes")
    public static String buildComboBoxDataSource(List<Map> list, String colID, String colName) {
        StringBuilder sBuilder = new StringBuilder();

        sBuilder.append("[");
        for (Map map : list) {
            sBuilder.append("{\"id\":\"").append(map.get(colID).toString()).append("\", \"text\":\"")
                    .append(map.get(colName).toString()).append("\"},");
        }
        sBuilder.deleteCharAt(sBuilder.length() - 1).append("]");

        return sBuilder.toString();
    }

    /**
     * 基于 java 对象，构建EasyUi 树
     * @param data 数据源
     * @param easyUiIdFieldName 哪个字段映射到EASYUI 的 IdFidld 中
     * @param easyUiTextFieldName 哪个字段映射到 textField 中
     * @param childFieldName  子关联字段
     * @param parentFieldName  父关联字段
     * @param fieldParentRootVaule 值为rootValue 的是根对象
     * @return
     */
    public  EasyUITree buildEasyUiTree(List<T> data,String easyUiIdFieldName, String easyUiTextFieldName, String parentFieldName,String childFieldName,Object fieldParentRootVaule)throws Exception{
        EasyUITree rootTreeNode = new EasyUITree();
        rootTreeNode.setId("empty");
        rootTreeNode.setText("根节点");
        if (CollectionUtils.isEmpty(data)){
            return  null;
        }
        Class<?> aClass = data.get(0).getClass();
        Field childField = getField(aClass,childFieldName);
        Field idField =  getField(aClass,easyUiIdFieldName);
        Field textField =  getField(aClass,easyUiTextFieldName);
        Field parentField =  getField(aClass,parentFieldName);

        if (childField==null||idField==null||textField==null||parentField==null){
            throw  new RuntimeException("没有找到对应字段，请检查字段名是否正确");
        }
        authField(idField);
        authField(textField);
        authField(parentField);
        authField(childField);
        buildChildTrennNodes(rootTreeNode,data,fieldParentRootVaule,idField,textField,parentField,childField);
        return rootTreeNode;
    }


//todo 前端指定的id字段 不是父子关联字段
    /**
     * 获取子节点
     * @param tree 树根
     * @param data 数据
     * @param value 起始值
     * @param idField id字段
     * @param textField text字段
     */
    private  void  buildChildTrennNodes(EasyUITree tree, List data, Object value, Field idField, Field textField,Field parentField,Field childField){

        data.stream().forEach(t->{
                 try{
                    Object o = parentField.get(t);
                    if (o instanceof String){
                        if (((String) o).equalsIgnoreCase((String) value)){
                            buildTreeNode(tree, idField, textField, childField, t);
                        }
                    }else {
                        if (value==o){
                            buildTreeNode(tree, idField, textField, childField, t);
                        }
                    }
                 }catch (Exception e){
                     System.out.println("获取值错误");
                     e.printStackTrace();
                 }
        });
        List<EasyUITree> children = tree.getChildren();
        if (!CollectionUtils.isEmpty(children)){
            children.stream().forEach(t->{
                buildChildTrennNodes(t,data,t.getAttributes(),idField,textField,parentField,childField);
            });
        }else {
            return;
        }
    }

    private void buildTreeNode(EasyUITree tree, Field idField, Field textField, Field childField, Object t) throws IllegalAccessException {
        EasyUITree tempTree = new EasyUITree();
        tempTree.setId(idField.get(t));
        tempTree.setText(String.valueOf(textField.get(t)));
        tempTree.setAttributes(childField.get(t));
        tree.addChild(tempTree);
    }

    /**
     * 查找属性
     * @param clazz
     * @param fieldName
     * @return
     */
    private Field getField(Class clazz,String fieldName){
        Field result=null;
        while (true){
            try {
                result = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {}
            if (result==null&&clazz!=Object.class){
                clazz=clazz.getSuperclass();
            }else {
                return result;
            }
        }
    }

    /**
     * 授权
     * @param field
     */
    private void authField(Field field){
       if (field!=null){
           field.setAccessible(true);
       }
    }





}
