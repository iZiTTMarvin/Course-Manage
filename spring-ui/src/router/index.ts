import {createRouter, createWebHistory} from 'vue-router'
import {ElMessage, ElMessageBox} from 'element-plus'
import type {Action} from 'element-plus'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'login',
            component: () => import('@/views/LoginView.vue'),
        },
        {
            path: '/home',
            name: 'home',
            component: () => import('@/views/LayoutView.vue'),
            meta: {requireAuth: true},
            beforeEnter: (to, from, next) => {
                //@ts-ignore
                if (to.meta.requireAuth) { //判断是否需要授权
                    if (localStorage.getItem('token')) {
                        next();
                    } else {
                        ElMessageBox.alert('抱歉，请先登录，您无权限查看！', '提示', {
                            confirmButtonText: '确认',
                            callback: (action: Action) => {
                                router.push('/');
                                ElMessage.info({message: `已返回登录页`, grouping: true, showClose: true})
                            },
                        })
                    }
                } else {
                    next();
                }
            }
        },
        {
            path: '/:pathMatch(.*)',
            name: '404',
            component: () => import('../views/ErrorView.vue')
        }
    ]
})

export default router
