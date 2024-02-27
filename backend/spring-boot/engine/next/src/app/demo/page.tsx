'use client'

import React, {useEffect, useState} from "react";
import {CodeEditor} from "@jansora/monaco";
import GetDarkMode from "@jansora/ui/esm/components/enhanced/plugins/GetDarkMode";
import {toast} from "sonner";
import {useDebounceFn} from "ahooks";
import {HoverCard, HoverCardContent, HoverCardTrigger,} from "@jansora/ui/esm/components/ui/hover-card";
import {Button} from "@jansora/ui/esm/components/ui/button";
import {Card, CardContent, CardFooter, CardHeader, CardTitle,} from "@jansora/ui/esm/components/ui/card"
import {
    Accordion,
    AccordionContent,
    AccordionItem,
    AccordionTrigger,
} from "@jansora/ui/esm/components/ui/accordion"

import {
    Form,
    FormControl,
    FormDescription,
    FormField,
    FormItem,
    FormLabel,
    FormMessage,
} from "@jansora/ui/esm/components/ui/form"
import { Input } from "@jansora/ui/esm/components/ui/input"

import {
    Tabs,
    TabsContent,
    TabsList,
    TabsTrigger,
} from "@jansora/ui/esm/components/ui/tabs"
import {useForm} from "react-hook-form";
import {fetchClient} from "@/lib/fetch/client/fetch-client";
const meta = {
    variables: {
        '税前工资': ' 3000.23 ',
        '工作日加班天数': ' 5.3 ',
        '周末加班天数': ' 2.5 ',
        '节假日加班天数': ' 2 ',
        '绩效': ' 1.0 ',
        '试用期系数': ' 0.8  ',
        '绩效系数': ' 0.30  ',
        '缺勤天数': ' 1.5 ',
        '销售金额': ' 1000000 ',
        '通报处罚': ' 500 ',

    },
    functions: {
        '销售提成': '5000',
        '五险一金计算': ' 3000 ',
        '个税计算': ' 2300 ',
    },
    formulas: {
        '基础工资模块': {
            '试用期工资': ' ( 税前工资 * 试用期系数) '
        },
        '绩效模块': {
            '非绩效工资': ' 基础工资模块 * ( 1 - 绩效系数) * 绩效系数  ',
            '基础绩效工资': '基础工资模块 * 绩效系数 * 绩效 ',
            '全勤工资': ' 300 ',
            '销售提成工资': ' 销售提成 '
        },
        '公共模块': {
            '五险一金扣款': ' ( 五险一金计算) ',
            '个人所得税扣款': ' (个税计算) '
        },
        '加班模块': {
            '工作日加班工资': ' 100 * 工作日加班天数 ',
            '周末加班工资': ' 500 * 周末加班天数 ',
            '节假日加班工资': ' 1000 * 节假日加班天数 ',
        },
        '扣款模块': {
            '缺勤工资': ' 缺勤天数 * 50 ',
            '其他扣款': ' 通报处罚 ',
        },
    },
    finalFormula: '(绩效模块) + (加班模块) - (公共模块) - (扣款模块)'
}

const updateVar = (value) => {

    try{
        // eslint-disable-next-line
        return Function('"use strict";return (' + value + ')')()

    }
    catch (e) {
        toast.error('元数据表达式解析出现错误')
    }
    return {}
}
export default function Page() {

    const [code, setCode] = useState(JSON.stringify(meta, null, 2));
    const [metadata, setMetadata] = useState(meta);

    const [updateEditor, setUpdateEditor] = useState(false)

    const [loadingCodeEditor, setLoadingCodeEditor] = useState(false)

    const dark = GetDarkMode();

    // 1. Define your form.
    const form = useForm({
        defaultValues: {
           ...metadata.variables
        },
    })


    async function onSubmit(values: any) {

        console.log(values)
        // setLoading(true)

        const result = await fetchClient("/salary/calculate", {
            method: 'PUT',
            body: JSON.stringify({...metadata, variables: values}),
        })
        // setLoading(false)

        if (result.data.status) {
            toast.success('试算成功')
            // router.push(`/notebook/${result.data.data.id}`)
        }

        // console.log("onSubmit", data, result)
    }

    const {run: updateVarDebounce} = useDebounceFn(
        (value) => setMetadata(updateVar(value)),
        {
            wait: 2000,
        },
    );
    useEffect(() => {
        updateVarDebounce(  code)
    }, [code])

    useEffect(() => {
        setUpdateEditor(true)

        setTimeout(() => {
            setLoadingCodeEditor(false)
        }, 0)
    }, [updateEditor])

    return (
        <>
            <div className="flex p-8">
                <div className="w-1/3">
                    <small className="text-sm font-medium leading-none mb-5 block">元数据编辑</small>
                    {
                        !loadingCodeEditor &&
                        <CodeEditor
                            dark={dark}
                            force={false}
                            id={"code-editor-drools-run"}
                            language={"json"}
                            value={code}
                            onChange={setCode}
                            style={{height:450}}
                        />
                    }
                </div>
                <div className="w-2/3 ">
                    <Accordion type="multiple" className="w-full px-5">
                        <AccordionItem value="item-1">
                            <AccordionTrigger className="hover:no-underline">高频功能点</AccordionTrigger>
                            <AccordionContent>
                                <div>
                                    {
                                        Object.keys(metadata.functions).map((key, index) => {
                                            return (
                                                <HoverCard key={index}>
                                                    <HoverCardTrigger asChild>
                                                        <Button size="sm" variant="default" className="mr-3">{key}</Button>
                                                    </HoverCardTrigger>
                                                    <HoverCardContent className="w-80">
                                                        <div className="flex justify-between space-x-4">
                                                            <div className="space-y-1">
                                                                <h4 className="text-sm font-semibold">计算公式</h4>
                                                                <p className="text-sm text-green-500">
                                                                    {metadata.functions[key]}
                                                                </p>
                                                                <div className="flex items-center pt-2">
                                                        <span className="text-xs text-muted-foreground">
                                                          计算公式明细
                                                        </span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </HoverCardContent>
                                                </HoverCard>
                                            )
                                        })
                                    }

                                </div>

                            </AccordionContent>
                        </AccordionItem>
                    </Accordion>
                    <Tabs defaultValue="calculate" className="w-full">
                        <TabsList className="grid w-full grid-cols-2">
                            <TabsTrigger value="template">计算模板</TabsTrigger>
                            <TabsTrigger value="calculate">工资试算</TabsTrigger>
                        </TabsList>
                        <TabsContent value="template" className="pt-8">
                    <div>
                        <small className="text-sm font-medium leading-none mb-5 block">高频功能点</small>

                        <small className="text-sm font-medium leading-none mb-5 block mt-5">工资计算块 </small>
                        <div className="flex flex-wrap ">
                        {
                            Object.keys(metadata.formulas).map((formula, index) => {
                                return <Card className="w-[450px] mt-2 mr-2" key={index}>
                                    <CardHeader>
                                        <CardTitle>{formula}</CardTitle>
                                        {/*<CardDescription>Deploy your new project in one-click.</CardDescription>*/}
                                    </CardHeader>
                                    <CardContent>
                                        <div className="flex flex-col ">
                                            {
                                                Object.keys(metadata.formulas[formula]).map((item, index) => {
                                                    return <span key={index}>
                                                        <span className="text-sm text-muted-foreground w-[150px] inline-block text-right">
                                                            {item}
                                                        </span>
                                                        :
                                                        <span className="text-sm text-green-500">
                                                            {metadata.formulas[formula][item]}
                                                        </span>
                                                        </span>
                                                })
                                            }
                                        </div>

                                        {/*<Separator orientation="horizontal" className="my-2"/>*/}


                                    </CardContent>
                                    <CardFooter className="flex justify-between">
                             <span>
                                            <span className="text-sm text-muted-foreground text-yellow-500 inline-block">
                                                {formula}
                                            </span>
                                            <span className="mx-1"> = </span>
                                            <span className="text-sm text-green-500">
                                                {Object.keys(metadata.formulas[formula]).join(" + ")}
                                            </span>
                                            </span>

                                    </CardFooter>
                                </Card>
                            })
                        }

                        </div>

                        <p className="mt-3 text-lg">最终工资计算
                            <code className="text-cyan-500"> {metadata.finalFormula}</code>
                        </p>

                    </div>
                        </TabsContent>
                        <TabsContent value="calculate" className="pt-8">
                            <div>
                                <small className="text-sm font-medium leading-none mb-5 block">输入变量</small>

                                <div>
                                    <Form {...form}>
                                        <form onSubmit={form.handleSubmit(onSubmit)} className="flex items-center flex-wrap">
                                            {
                                                Object.keys(metadata.variables).map((key, index) => {
                                                    return <FormField
                                                        control={form.control}
                                                        key={index}
                                                        // @ts-ignore
                                                        name={key}
                                                        render={({ field }) => (
                                                            <FormItem className="mr-5 mb-5">
                                                                <FormLabel className="text-cyan-500">{key}</FormLabel>
                                                                <FormControl>
                                                                    <Input placeholder={key} {...field} />
                                                                </FormControl>
                                                                <FormMessage />
                                                            </FormItem>
                                                        )}
                                                    />
                                                })
                                            }
                                            <Button size="sm" type="submit" className="py-1 mt-2">试算</Button>
                                        </form>
                                    </Form>
                                </div>


                                <small className="text-sm font-medium leading-none mb-5 block mt-5"> 工资试算 </small>
                                <div className="flex flex-wrap ">
                                    {
                                        Object.keys(metadata.formulas).map((formula, index) => {
                                            return <Card className="w-[450px] mt-2 mr-2" key={index}>
                                                <CardHeader>
                                                    <CardTitle>{formula}</CardTitle>
                                                    {/*<CardDescription>Deploy your new project in one-click.</CardDescription>*/}
                                                </CardHeader>
                                                <CardContent>
                                                    <div className="flex flex-col ">
                                                        {
                                                            Object.keys(metadata.formulas[formula]).map((item, index) => {
                                                                return <span key={index}>
                                                        <span className="text-sm text-muted-foreground w-[150px] inline-block text-right">
                                                            {item}
                                                        </span>
                                                        :
                                                        <span className="text-sm text-green-500">
                                                            {metadata.formulas[formula][item]}
                                                        </span>
                                                        </span>
                                                            })
                                                        }
                                                    </div>

                                                    {/*<Separator orientation="horizontal" className="my-2"/>*/}


                                                </CardContent>
                                                <CardFooter className="flex justify-between">
                             <span>
                                            <span className="text-sm text-muted-foreground text-yellow-500 inline-block">
                                                {formula}
                                            </span>
                                            <span className="mx-1"> = </span>
                                            <span className="text-sm text-green-500">
                                                {Object.keys(metadata.formulas[formula]).join(" + ")}
                                            </span>
                                            </span>

                                                </CardFooter>
                                            </Card>
                                        })
                                    }

                                </div>

                                <p className="mt-3 text-lg">最终工资试算
                                    <code className="text-cyan-500"> {metadata.finalFormula}</code>
                                </p>

                            </div>
                        </TabsContent>
                    </Tabs>
                </div>

            </div>

        </>
    )
}
