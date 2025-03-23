import { defineConfig } from "vite";
import react from "@vitejs/plugin-react-swc";

// https://vite.dev/config/
export default defineConfig({
    plugins: [react()],
    server: {
        port: 3000,
        host: true,
        watch: {
            usePolling: true, // 🔥 파일 변경 감지를 확실하게 하기 위해 필요
        },
    },
});
