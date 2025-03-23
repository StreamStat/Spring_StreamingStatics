module.exports = {
    root: true,
    env: {
        browser: true,
        es2021: true,
    },
    extends: [
        "eslint:recommended",
        "plugin:@typescript-eslint/recommended",
        "plugin:react/recommended",
        "plugin:react-hooks/recommended",
        "plugin:prettier/recommended",
    ],
    parser: "@typescript-eslint/parser",
    parserOptions: {
        ecmaVersion: "latest",
        sourceType: "module",
    },
    settings: {
        react: {
            version: "detect",
        }
    },
    plugins: ["@typescript-eslint", "react", "react-hooks", "prettier"],
    rules: {
        "react/react-in-jsx-scope": "off", // React 17+에서는 필요 없음
        "prettier/prettier": "error", // Prettier 규칙 적용
        "semi": ["error", "always"], // 세미콜론 강제 적용
        "quotes": ["error", "double"], // 큰따옴표 강제 적용
        "indent": ["error", 4], // 들여쓰기 4칸
    }
};
