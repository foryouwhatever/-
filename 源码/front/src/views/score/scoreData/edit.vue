<template>
<div>
    <Card>
        <div slot="title">
            <div class="edit-head">
                <a @click="close" class="back-title">
                    <Icon type="ios-arrow-back" />返回
                </a>
                <div class="head-name">编辑加扣分数据</div>
                <span></span>
                <a @click="close" class="window-close">
                    <Icon type="ios-close" size="31" class="ivu-icon-ios-close" />
                </a>
            </div>
        </div>
        <Form ref="form" :model="form" :label-width="100" :rules="formValidate" label-position="left">
            <FormItem label="评分项目" prop="projectId">
                <Select v-model="form.projectId" clearable @on-change="changeProject" placeholder="请选择评分项目..." style="width:570px">
                    <Option v-for="(item,index) in projectList" :key="index" :value="item.id">{{ item.title }}</Option>
                </Select>
            </FormItem>
            <FormItem label="评分细则">
                <Input v-model="tempContext" readonly type="textarea" :autosize="true" placeholder="请先选择评分项目..." style="width:570px" />
            </FormItem>
            <FormItem label="学生" prop="studentId">
                <Select v-model="form.studentId" clearable placeholder="请选择学生" style="width:570px">
                    <Option v-for="(item,index) in studentList" :key="index" :value="item.id">{{ item.nickname }}</Option>
                </Select>
            </FormItem>
            <FormItem label="加分数值" prop="value">
                <InputNumber v-model="form.value" min="-5000" max="5000" placeholder="请输入加分数值" style="width:570px"></InputNumber>
            </FormItem>
            <FormItem label="加分原因" prop="reason">
                <Input v-model="form.reason" type="textarea" :rows="4" clearable maxlength="240" placeholder="请输入加分原因" show-word-limit style="width:570px" />
            </FormItem>
            <FormItem label="加分时间" prop="time">
                <Input v-model="form.time" readonly style="width:570px" />
            </FormItem>
            <FormItem label="操作人" prop="workName">
                <Input v-model="form.workName" readonly style="width:570px" />
            </FormItem>
            <Form-item class="br">
                <Button @click="handleSubmit" :loading="submitLoading" type="primary">提交并保存</Button>
                <Button @click="handleReset">重置</Button>
                <Button type="dashed" @click="close">关闭</Button>
            </Form-item>
        </Form>
    </Card>
</div>
</template>

<script>
import {
    editScoreData,
    getScoreProjectList,
    getUserList,
    getOneScoreProjectList
} from "./api.js";
export default {
    name: "edit",
    components: {},
    props: {
        data: Object
    },
    data() {
        return {
            submitLoading: false, // 表单提交状态
            form: { // 添加或编辑表单对象初始化数据
                projectId: "",
                projectName: "",
                studentId: "",
                studentName: "",
                value: 0,
                reason: "",
                time: "",
                workId: "",
                workName: "",
            },
            // 表单验证规则
            formValidate: {},
            projectList: [],
            studentList: [],
            tempContext: ""
        };
    },
    methods: {
        init() {
            this.getScoreProjectListFx();
            this.getUserListFx();
            this.handleReset();
            this.form = this.data;
            this.changeProject(this.form.projectId);
        },
        changeProject(e) {
            var that = this;
            getOneScoreProjectList({
                id: e
            }).then(res => {
                if (res.success) {
                    that.tempContext = res.result.content;
                }
            })
        },
        getUserListFx() {
            var that = this;
            that.studentList = [];
            getUserList({
                type: 0
            }).then(res => {
                if (res.success) {
                    that.studentList = res.result;
                }
            })
        },
        getScoreProjectListFx() {
            var that = this;
            that.projectList = [];
            getScoreProjectList().then(res => {
                if (res.success) {
                    that.projectList = res.result;
                }
            })
        },
        handleReset() {
            this.$refs.form.resetFields();
        },
        handleSubmit() {
            this.$refs.form.validate(valid => {
                if (valid) {
                    editScoreData(this.form).then(res => {
                        this.submitLoading = false;
                        if (res.success) {
                            this.$Message.success("操作成功");
                            this.submited();
                        }
                    });
                }
            });
        },
        close() {
            this.$emit("close", true);
        },
        submited() {
            this.$emit("submited", true);
        }
    },
    mounted() {
        this.init();
    }
};
</script>

<style lang="less">
// 建议引入通用样式 具体路径自行修改 可删除下面样式代码
// @import "../../../styles/single-common.less";
.edit-head {
    display: flex;
    align-items: center;
    justify-content: space-between;
    position: relative;

    .back-title {
        color: #515a6e;
        display: flex;
        align-items: center;
    }

    .head-name {
        display: inline-block;
        height: 20px;
        line-height: 20px;
        font-size: 16px;
        color: #17233d;
        font-weight: 500;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }

    .window-close {
        z-index: 1;
        font-size: 12px;
        position: absolute;
        right: 0px;
        top: -5px;
        overflow: hidden;
        cursor: pointer;

        .ivu-icon-ios-close {
            color: #999;
            transition: color .2s ease;
        }
    }

    .window-close .ivu-icon-ios-close:hover {
        color: #444;
    }
}
</style>
