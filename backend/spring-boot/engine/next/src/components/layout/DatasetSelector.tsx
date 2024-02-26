"use client"

import * as React from "react"
import {Dispatch} from "react"
import {CaretSortIcon, CheckIcon} from "@radix-ui/react-icons"
import {PopoverProps} from "@radix-ui/react-popover"


import {Button} from "@jansora/ui/esm/components/ui/button"
import {Command, CommandEmpty, CommandGroup, CommandInput, CommandItem,} from "@jansora/ui/esm/components/ui/command"
import {Popover, PopoverContent, PopoverTrigger,} from "@jansora/ui/esm/components/ui/popover"
import {cn} from "@jansora/ui/esm/lib/utils";

export declare class Dataset {
    name: string
    payload: string
}


interface DatasetSelectorProps extends PopoverProps {
    datasets: Dataset[]
    dataset: Dataset
    setDataset: Dispatch<Dataset>
}

export function DatasetSelector({ dataset, datasets, setDataset, ...props }: DatasetSelectorProps) {
  const [open, setOpen] = React.useState(false)

  return (
      <Popover open={open} onOpenChange={setOpen} {...props}>
        <PopoverTrigger asChild>
          <Button
              variant="outline"
              role="combobox"
              aria-label="Load a Dataset..."
              aria-expanded={open}
              className="flex-1 justify-between md:max-w-[300px] lg:max-w-[300px] w-full"
          >
              {!!dataset.name ? dataset.name : "加载数据集中..."}
                <CaretSortIcon className="ml-2 h-4 w-4 shrink-0 opacity-50" />
          </Button>
        </PopoverTrigger>
        <PopoverContent className="w-[300px] p-0">
          <Command>
            <CommandInput placeholder="搜索预置数据集..." />
            <CommandEmpty>未找到数据集.</CommandEmpty>
            <CommandGroup heading="">
              {datasets.map((_dataset) => (
                  <CommandItem
                      key={_dataset.name}
                      value={_dataset.name}
                      onClick={() => {
                          setDataset(dataset)
                          // setDataset(_dataset)
                          setOpen(false)
                      }}
                      onSelect={() => {
                          console.log("do sonmething", )
                          setDataset(_dataset)
                        // setDataset(_dataset)
                        setOpen(false)
                      }}
                  >
                    {_dataset.name}
                    <CheckIcon
                        className={cn(
                            "ml-auto h-4 w-4",
                            dataset.name === _dataset.name
                                ? "opacity-100"
                                : "opacity-0"
                        )}
                    />
                  </CommandItem>
              ))}
            </CommandGroup>
            {/*<CommandGroup className="pt-0">*/}
            {/*  <CommandItem onSelect={() => router.push("/examples")}>*/}
            {/*    More examples*/}
            {/*  </CommandItem>*/}
            {/*</CommandGroup>*/}
          </Command>
        </PopoverContent>
      </Popover>
  )
}
