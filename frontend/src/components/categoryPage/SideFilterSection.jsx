import React ,{useState} from 'react'

import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Checkbox } from "@/components/ui/checkbox";
import {
  Field,
  FieldGroup,
  FieldLegend,
} from "@/components/ui/field"
import { Label } from "@/components/ui/label"



function SideFilterSection({selectedBrand, handleSelectedBrand, brands}) {



    return (
        <section className=' space-y-4 lg:sticky! lg:top-24!'>
            <Card className="border-primary/20 bg-primary/5">
                <CardContent>
                    <FieldLegend variant='legend' className="mt-4">
                        Choose Brand
                    </FieldLegend>
                    <FieldGroup className="max-w-sm mt-6" >
                        {brands && brands.map(brand => {
                            const name = brand?.name
                            console.log(name)
                            
                            return (
                            <Field key={name} orientation='horizontal'>
                                <Checkbox id={name}
                                checked={selectedBrand.includes(name)}
                                onCheckedChange={(checked) => {
                                    console.log(checked)
                                    handleSelectedBrand(checked,name)
                                }}
                                 name={name} />
                                <Label htmlFor={name} className="text-muted-foreground"> {name}</Label>
                            </Field>
                        )})}

                    </FieldGroup>
                </CardContent>
            </Card>

        </section>
    )
}

export default SideFilterSection