package com.example.springboot.common.generator;

public class JsonToJsFunction {
    public static void main(String[] args) {
        // 给定的JSON字符串
        String jsonStr = """
                {
                    "paths": {
                        "/userUserMenuEntity/update": {
                            "post": {
                                "tags": [
                                    "user-menu-controller"
                                ],
                                "summary": "修改",
                                "operationId": "updateUserMenu",
                                "requestBody": {
                                    "content": {
                                        "application/json": {
                                            "schema": {
                                                "$ref": "#/components/schemas/UserMenuEntity"
                                            }
                                        }
                                    },
                                    "required": true
                                },
                                "responses": {
                                    "200": {
                                        "description": "OK",
                                        "content": {
                                            "*/*": {
                                                "schema": {
                                                    "$ref": "#/components/schemas/ResultObject"
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                """;

        // 从JSON字符串中提取operationId
        String operationId = extractOperationId(jsonStr);
        String operationPath = extractPath(jsonStr);

        // 构建JavaScript函数字符串
        String jsFunction = "export function " + operationId + "(params) {\n" +
                "  return request.post(`" + operationPath + "`, params);\n" +
                "}";

        // 输出结果
        System.out.println(jsFunction);
    }

    // 辅助方法，从JSON字符串中提取operationId
    private static String extractOperationId(String jsonStr) {
        // 定位到operationId关键字，并提取其值
        int start = jsonStr.indexOf("\"operationId\": \"") + "\"operationId\": \"".length();
        int end = jsonStr.indexOf("\"", start);
        return jsonStr.substring(start, end);
    }

    private static String extractPath(String jsonStr) {
        // 定位到operationId关键字，并提取其值
        int start = jsonStr.indexOf("\"operationId\": \"") + "\"operationId\": \"".length();
        int end = jsonStr.indexOf("\"", start);
        return jsonStr.substring(start, end);
    }
}