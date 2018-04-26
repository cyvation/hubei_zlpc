package com.start.boot.support.utils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * EasyUI控件数据源json生成帮助类
 * @author Administrator
 *
 */
public final class EasyUIHelper {

    /**
     * 转换List<Map>中数据为EasyUI下拉框树形控件数据源JSON 若要展开顶级节点，则对生成的字符串用
     * jsonString.replaceFirst("closed", "open")
     *
     * @param list
     *            数据源
     * @param colID
     *            ID列
     * @param colPID
     *            父ID列
     * @param colName
     *            显示列
     * @param rootID
     *            顶级父ID
     * @return JSON字符串
     */
    @SuppressWarnings("rawtypes")
    public static String buildTreeDataSourceWithoutIconCol(List<Map> list, String colID, String colPID, String colName,
                                                           String rootID) {
        return buildTreeListDataSource(list, colID, colPID, colName, null, rootID);
    }

    /**
     * 转换List<Map>中数据为EasyUI树形控件数据源JSON 若要展开顶级节点，则对生成的字符串用
     * jsonString.replaceFirst("closed", "open")
     *
     * @param list
     *            数据源
     * @param colID
     *            ID列
     * @param colPID
     *            父ID列
     * @param colName
     *            显示列
     * @param iconCol
     *            自定义图标列
     * @param rootID
     *            顶级父ID
     * @return JSON字符串
     */
    @SuppressWarnings("rawtypes")
    public static String buildTreeListDataSource(List<Map> list, String colID, String colPID, String colName,
                                                 String iconCol, String rootID) {

        if (list == null || list.isEmpty() || list.size() <= 0)
            return "[]";

        if ((colID == null || colID.isEmpty())
                || (colPID == null || colPID.isEmpty() || (colName == null || colName.isEmpty())))
            return "[]";

        // 筛选出顶级节点下第一级节点
        List<Map> fList = (rootID == null || rootID.isEmpty())
                ? list.parallelStream().filter(map -> (map.get(colPID) == null || map.get(colPID).toString().isEmpty()))
                .collect(Collectors.toList())
                : list.parallelStream().filter(map -> map.get(colPID).toString().equals(rootID))
                .collect(Collectors.toList());

        // 递归拼接JSON字符串
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("[");
        if (iconCol != null && !iconCol.isEmpty()) {
            for (Map map : fList) {
                sBuilder.append("{");
                sBuilder.append("\"id\":\"").append(map.get(colID).toString()).append("\", \"text\":\"")
                        .append(map.get(colName).toString()).append("\", \"iconCls\":\"")
                        .append(map.get(iconCol).toString()).append("\"");

                // 自定义列
                sBuilder.append(",\"attributes\":{");
                Iterator iterator = map.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    sBuilder.append("\"").append(entry.getKey().toString()).append("\":\"")
                            .append(entry.getValue() == null ? ""
                                    : entry.getValue().toString().replace("\b", "\b").replace("\t", "\\t")
                                    .replace("\n", "\\n").replace("\f", "\\f").replace("\r", "\\r")
                                    .replace("\"", "\\\""))
                            .append("\",");
                }
                sBuilder.deleteCharAt(sBuilder.length() - 1).append("}");
                
                if (list.parallelStream()
                        .anyMatch(mapI -> mapI.get(colPID).toString().equals(map.get(colID).toString()))) {
                    sBuilder.append(", \"state\":\"closed\",\"children\":").append(EasyUIHelper
                            .buildTreeListDataSource(list, colID, colPID, colName, iconCol, map.get(colID).toString()));
                }

                sBuilder.append("},");
            }
        } else {
            for (Map map : fList) {
                sBuilder.append("{");
                sBuilder.append("\"id\":\"").append(map.get(colID).toString()).append("\", \"text\":\"")
                        .append(map.get(colName).toString()).append("\"");
                
                // 自定义列
                sBuilder.append(",\"attributes\":{");
                Iterator iterator = map.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    sBuilder.append("\"").append(entry.getKey().toString()).append("\":\"")
                            .append(entry.getValue() == null ? ""
                                    : entry.getValue().toString().replace("\b", "\b").replace("\t", "\\t")
                                    .replace("\n", "\\n").replace("\f", "\\f").replace("\r", "\\r")
                                    .replace("\"", "\\\""))
                            .append("\",");
                }
                sBuilder.deleteCharAt(sBuilder.length() - 1).append("}");
                
                if (list.parallelStream()
                        .anyMatch(mapI -> mapI.get(colPID).toString().equals(map.get(colID).toString()))) {
                    sBuilder.append(", \"state\":\"closed\",\"children\":").append(EasyUIHelper
                            .buildTreeListDataSource(list, colID, colPID, colName, iconCol, map.get(colID).toString()));
                }

                sBuilder.append("},");
            }
        }

        sBuilder.deleteCharAt(sBuilder.length() - 1).append("]");

        return sBuilder.toString().replaceFirst("\"state\":\"closed\"", "\"state\":\"open\"");
    }

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
        if (list == null || list.isEmpty() || list.size() <= 0)
            return "[]";

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
     * 转换List<Map>中数据为EasyUI数据表控件数据源JSON
     *
     * @param list
     *            数据源
     * @param total
     *            总数
     * @return JSON字符串
     */
    @SuppressWarnings("rawtypes")
    public static String buildDataGridDataSource(List<Map> list, int total) {

        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("{\"total\":").append(total).append(",\"rows\":[");
        if (list == null || list.isEmpty())
            return sBuilder.append("]}").toString();

        for (Map map : list) {
            sBuilder.append("{");

            // 循环遍历列
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                sBuilder.append("\"").append(entry.getKey().toString()).append("\":\"")
                        .append(entry.getValue() == null ? ""
                                : entry.getValue().toString().replace("\b", "\b").replace("\t", "\\t")
                                .replace("\n", "\\n").replace("\f", "\\f").replace("\r", "\\r")
                                .replace("\"", "\\\""))
                        .append("\",");
                // .append(entry.getValue() == null ? "" :
                // entry.getValue().toString().replace("\r\n",
                // "<br>").replace("\n", "<br>")).append("\",");
            }

            sBuilder.deleteCharAt(sBuilder.length() - 1).append("},");
        }

        return sBuilder.deleteCharAt(sBuilder.length() - 1).append("]}").toString();
    }

    /**
     * 转换List<Map>中数据为EasyUI树形DataGrid控件数据源JSON
     *
     * @param list
     *            数据源
     * @param total
     *            总数
     * @param colPID
     *            父ID列
     * @return JSON字符串
     */
    @SuppressWarnings("rawtypes")
    public static String buildTreeGridDataSource(List<Map> list, int total, String colPID) {
        return buildTreeGridDataSource(list, total, colPID, null);
    }

    /**
     * 转换List<Map>中数据为EasyUI树形DataGrid控件数据源JSON
     *
     * @param list
     *            数据源
     * @param total
     *            总数
     * @param colPID
     *            父ID列
     * @param iconCol
     *            图标列
     * @return JSON字符串
     */
    @SuppressWarnings("rawtypes")
    public static String buildTreeGridDataSource(List<Map> list, int total, String colPID, String iconCol) {

        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("{\"total\":").append(total).append(",\"rows\":[");

        if (iconCol != null && !iconCol.isEmpty()) {
            for (Map map : list) {

                sBuilder.append("{");
                // 循环遍历列
                Iterator iterator = map.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    sBuilder.append("\"")
                            .append(entry.getKey().equals(colPID) ? "_parentId" : entry.getKey().toString())
                            .append("\":\"")
                            .append(entry.getValue() == null ? ""
                                    : entry.getValue().toString().replace("\r\n", "<br>").replace("\n", "<br>"))
                            .append("\",");
                }
                sBuilder.append("\"iconCls\":\"").append(map.get(iconCol).toString()).append("\",");
                sBuilder.deleteCharAt(sBuilder.length() - 1).append("},");
            }
        } else {
            for (Map map : list) {

                sBuilder.append("{");
                // 循环遍历列
                Iterator iterator = map.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    sBuilder.append("\"")
                            .append(entry.getKey().equals(colPID) ? "_parentId" : entry.getKey().toString())
                            .append("\":\"")
                            .append(entry.getValue() == null ? ""
                                    : entry.getValue().toString().replace("\r\n", "<br>").replace("\n", "<br>"))
                            .append("\",");
                }
                sBuilder.deleteCharAt(sBuilder.length() - 1).append("},");
            }
        }

        return sBuilder.deleteCharAt(sBuilder.length() - 1).append("],\"footer\":[]}").toString();
    }
    

    public static String replaceFirst(String source, String pattern, String replacement) {
    	return source.isEmpty() ? "" : source.replaceFirst(pattern, replacement);
    }
}
